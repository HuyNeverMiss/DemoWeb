package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.NhanVien;
import com.mycompany.myapp.repository.NhanVienRepository;
import com.mycompany.myapp.service.criteria.NhanVienCriteria;
import com.mycompany.myapp.service.dto.NhanVienDTO;
import com.mycompany.myapp.service.mapper.NhanVienMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NhanVienResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NhanVienResourceIT {

    private static final String DEFAULT_MA_NHAN_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_NHAN_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_CHUC_VU = "AAAAAAAAAA";
    private static final String UPDATED_CHUC_VU = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/nhan-viens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private NhanVienMapper nhanVienMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNhanVienMockMvc;

    private NhanVien nhanVien;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhanVien createEntity(EntityManager em) {
        NhanVien nhanVien = new NhanVien().maNhanVien(DEFAULT_MA_NHAN_VIEN).chucVu(DEFAULT_CHUC_VU);
        return nhanVien;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhanVien createUpdatedEntity(EntityManager em) {
        NhanVien nhanVien = new NhanVien().maNhanVien(UPDATED_MA_NHAN_VIEN).chucVu(UPDATED_CHUC_VU);
        return nhanVien;
    }

    @BeforeEach
    public void initTest() {
        nhanVien = createEntity(em);
    }

    @Test
    @Transactional
    void createNhanVien() throws Exception {
        int databaseSizeBeforeCreate = nhanVienRepository.findAll().size();
        // Create the NhanVien
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);
        restNhanVienMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeCreate + 1);
        NhanVien testNhanVien = nhanVienList.get(nhanVienList.size() - 1);
        assertThat(testNhanVien.getMaNhanVien()).isEqualTo(DEFAULT_MA_NHAN_VIEN);
        assertThat(testNhanVien.getChucVu()).isEqualTo(DEFAULT_CHUC_VU);
    }

    @Test
    @Transactional
    void createNhanVienWithExistingId() throws Exception {
        // Create the NhanVien with an existing ID
        nhanVien.setId(1L);
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        int databaseSizeBeforeCreate = nhanVienRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanVienMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMaNhanVienIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanVienRepository.findAll().size();
        // set the field null
        nhanVien.setMaNhanVien(null);

        // Create the NhanVien, which fails.
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        restNhanVienMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isBadRequest());

        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNhanViens() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList
        restNhanVienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNhanVien").value(hasItem(DEFAULT_MA_NHAN_VIEN)))
            .andExpect(jsonPath("$.[*].chucVu").value(hasItem(DEFAULT_CHUC_VU)));
    }

    @Test
    @Transactional
    void getNhanVien() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get the nhanVien
        restNhanVienMockMvc
            .perform(get(ENTITY_API_URL_ID, nhanVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nhanVien.getId().intValue()))
            .andExpect(jsonPath("$.maNhanVien").value(DEFAULT_MA_NHAN_VIEN))
            .andExpect(jsonPath("$.chucVu").value(DEFAULT_CHUC_VU));
    }

    @Test
    @Transactional
    void getNhanViensByIdFiltering() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        Long id = nhanVien.getId();

        defaultNhanVienShouldBeFound("id.equals=" + id);
        defaultNhanVienShouldNotBeFound("id.notEquals=" + id);

        defaultNhanVienShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNhanVienShouldNotBeFound("id.greaterThan=" + id);

        defaultNhanVienShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNhanVienShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllNhanViensByMaNhanVienIsEqualToSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where maNhanVien equals to DEFAULT_MA_NHAN_VIEN
        defaultNhanVienShouldBeFound("maNhanVien.equals=" + DEFAULT_MA_NHAN_VIEN);

        // Get all the nhanVienList where maNhanVien equals to UPDATED_MA_NHAN_VIEN
        defaultNhanVienShouldNotBeFound("maNhanVien.equals=" + UPDATED_MA_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllNhanViensByMaNhanVienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where maNhanVien not equals to DEFAULT_MA_NHAN_VIEN
        defaultNhanVienShouldNotBeFound("maNhanVien.notEquals=" + DEFAULT_MA_NHAN_VIEN);

        // Get all the nhanVienList where maNhanVien not equals to UPDATED_MA_NHAN_VIEN
        defaultNhanVienShouldBeFound("maNhanVien.notEquals=" + UPDATED_MA_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllNhanViensByMaNhanVienIsInShouldWork() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where maNhanVien in DEFAULT_MA_NHAN_VIEN or UPDATED_MA_NHAN_VIEN
        defaultNhanVienShouldBeFound("maNhanVien.in=" + DEFAULT_MA_NHAN_VIEN + "," + UPDATED_MA_NHAN_VIEN);

        // Get all the nhanVienList where maNhanVien equals to UPDATED_MA_NHAN_VIEN
        defaultNhanVienShouldNotBeFound("maNhanVien.in=" + UPDATED_MA_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllNhanViensByMaNhanVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where maNhanVien is not null
        defaultNhanVienShouldBeFound("maNhanVien.specified=true");

        // Get all the nhanVienList where maNhanVien is null
        defaultNhanVienShouldNotBeFound("maNhanVien.specified=false");
    }

    @Test
    @Transactional
    void getAllNhanViensByMaNhanVienContainsSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where maNhanVien contains DEFAULT_MA_NHAN_VIEN
        defaultNhanVienShouldBeFound("maNhanVien.contains=" + DEFAULT_MA_NHAN_VIEN);

        // Get all the nhanVienList where maNhanVien contains UPDATED_MA_NHAN_VIEN
        defaultNhanVienShouldNotBeFound("maNhanVien.contains=" + UPDATED_MA_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllNhanViensByMaNhanVienNotContainsSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where maNhanVien does not contain DEFAULT_MA_NHAN_VIEN
        defaultNhanVienShouldNotBeFound("maNhanVien.doesNotContain=" + DEFAULT_MA_NHAN_VIEN);

        // Get all the nhanVienList where maNhanVien does not contain UPDATED_MA_NHAN_VIEN
        defaultNhanVienShouldBeFound("maNhanVien.doesNotContain=" + UPDATED_MA_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllNhanViensByChucVuIsEqualToSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where chucVu equals to DEFAULT_CHUC_VU
        defaultNhanVienShouldBeFound("chucVu.equals=" + DEFAULT_CHUC_VU);

        // Get all the nhanVienList where chucVu equals to UPDATED_CHUC_VU
        defaultNhanVienShouldNotBeFound("chucVu.equals=" + UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void getAllNhanViensByChucVuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where chucVu not equals to DEFAULT_CHUC_VU
        defaultNhanVienShouldNotBeFound("chucVu.notEquals=" + DEFAULT_CHUC_VU);

        // Get all the nhanVienList where chucVu not equals to UPDATED_CHUC_VU
        defaultNhanVienShouldBeFound("chucVu.notEquals=" + UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void getAllNhanViensByChucVuIsInShouldWork() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where chucVu in DEFAULT_CHUC_VU or UPDATED_CHUC_VU
        defaultNhanVienShouldBeFound("chucVu.in=" + DEFAULT_CHUC_VU + "," + UPDATED_CHUC_VU);

        // Get all the nhanVienList where chucVu equals to UPDATED_CHUC_VU
        defaultNhanVienShouldNotBeFound("chucVu.in=" + UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void getAllNhanViensByChucVuIsNullOrNotNull() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where chucVu is not null
        defaultNhanVienShouldBeFound("chucVu.specified=true");

        // Get all the nhanVienList where chucVu is null
        defaultNhanVienShouldNotBeFound("chucVu.specified=false");
    }

    @Test
    @Transactional
    void getAllNhanViensByChucVuContainsSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where chucVu contains DEFAULT_CHUC_VU
        defaultNhanVienShouldBeFound("chucVu.contains=" + DEFAULT_CHUC_VU);

        // Get all the nhanVienList where chucVu contains UPDATED_CHUC_VU
        defaultNhanVienShouldNotBeFound("chucVu.contains=" + UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void getAllNhanViensByChucVuNotContainsSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList where chucVu does not contain DEFAULT_CHUC_VU
        defaultNhanVienShouldNotBeFound("chucVu.doesNotContain=" + DEFAULT_CHUC_VU);

        // Get all the nhanVienList where chucVu does not contain UPDATED_CHUC_VU
        defaultNhanVienShouldBeFound("chucVu.doesNotContain=" + UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void getAllNhanViensByChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);
        ChiDaoTuyen chiDaoTuyen;
        if (TestUtil.findAll(em, ChiDaoTuyen.class).isEmpty()) {
            chiDaoTuyen = ChiDaoTuyenResourceIT.createEntity(em);
            em.persist(chiDaoTuyen);
            em.flush();
        } else {
            chiDaoTuyen = TestUtil.findAll(em, ChiDaoTuyen.class).get(0);
        }
        em.persist(chiDaoTuyen);
        em.flush();
        nhanVien.addChiDaoTuyen(chiDaoTuyen);
        nhanVienRepository.saveAndFlush(nhanVien);
        Long chiDaoTuyenId = chiDaoTuyen.getId();

        // Get all the nhanVienList where chiDaoTuyen equals to chiDaoTuyenId
        defaultNhanVienShouldBeFound("chiDaoTuyenId.equals=" + chiDaoTuyenId);

        // Get all the nhanVienList where chiDaoTuyen equals to (chiDaoTuyenId + 1)
        defaultNhanVienShouldNotBeFound("chiDaoTuyenId.equals=" + (chiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNhanVienShouldBeFound(String filter) throws Exception {
        restNhanVienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNhanVien").value(hasItem(DEFAULT_MA_NHAN_VIEN)))
            .andExpect(jsonPath("$.[*].chucVu").value(hasItem(DEFAULT_CHUC_VU)));

        // Check, that the count call also returns 1
        restNhanVienMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNhanVienShouldNotBeFound(String filter) throws Exception {
        restNhanVienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNhanVienMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingNhanVien() throws Exception {
        // Get the nhanVien
        restNhanVienMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNhanVien() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();

        // Update the nhanVien
        NhanVien updatedNhanVien = nhanVienRepository.findById(nhanVien.getId()).get();
        // Disconnect from session so that the updates on updatedNhanVien are not directly saved in db
        em.detach(updatedNhanVien);
        updatedNhanVien.maNhanVien(UPDATED_MA_NHAN_VIEN).chucVu(UPDATED_CHUC_VU);
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(updatedNhanVien);

        restNhanVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhanVienDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isOk());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
        NhanVien testNhanVien = nhanVienList.get(nhanVienList.size() - 1);
        assertThat(testNhanVien.getMaNhanVien()).isEqualTo(UPDATED_MA_NHAN_VIEN);
        assertThat(testNhanVien.getChucVu()).isEqualTo(UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void putNonExistingNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();
        nhanVien.setId(count.incrementAndGet());

        // Create the NhanVien
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhanVienDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();
        nhanVien.setId(count.incrementAndGet());

        // Create the NhanVien
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();
        nhanVien.setId(count.incrementAndGet());

        // Create the NhanVien
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNhanVienWithPatch() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();

        // Update the nhanVien using partial update
        NhanVien partialUpdatedNhanVien = new NhanVien();
        partialUpdatedNhanVien.setId(nhanVien.getId());

        partialUpdatedNhanVien.chucVu(UPDATED_CHUC_VU);

        restNhanVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhanVien.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNhanVien))
            )
            .andExpect(status().isOk());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
        NhanVien testNhanVien = nhanVienList.get(nhanVienList.size() - 1);
        assertThat(testNhanVien.getMaNhanVien()).isEqualTo(DEFAULT_MA_NHAN_VIEN);
        assertThat(testNhanVien.getChucVu()).isEqualTo(UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void fullUpdateNhanVienWithPatch() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();

        // Update the nhanVien using partial update
        NhanVien partialUpdatedNhanVien = new NhanVien();
        partialUpdatedNhanVien.setId(nhanVien.getId());

        partialUpdatedNhanVien.maNhanVien(UPDATED_MA_NHAN_VIEN).chucVu(UPDATED_CHUC_VU);

        restNhanVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhanVien.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNhanVien))
            )
            .andExpect(status().isOk());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
        NhanVien testNhanVien = nhanVienList.get(nhanVienList.size() - 1);
        assertThat(testNhanVien.getMaNhanVien()).isEqualTo(UPDATED_MA_NHAN_VIEN);
        assertThat(testNhanVien.getChucVu()).isEqualTo(UPDATED_CHUC_VU);
    }

    @Test
    @Transactional
    void patchNonExistingNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();
        nhanVien.setId(count.incrementAndGet());

        // Create the NhanVien
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nhanVienDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();
        nhanVien.setId(count.incrementAndGet());

        // Create the NhanVien
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();
        nhanVien.setId(count.incrementAndGet());

        // Create the NhanVien
        NhanVienDTO nhanVienDTO = nhanVienMapper.toDto(nhanVien);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNhanVien() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        int databaseSizeBeforeDelete = nhanVienRepository.findAll().size();

        // Delete the nhanVien
        restNhanVienMockMvc
            .perform(delete(ENTITY_API_URL_ID, nhanVien.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
