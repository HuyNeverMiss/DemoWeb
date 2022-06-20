package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.repository.KetQuaCongTacRepository;
import com.mycompany.myapp.service.criteria.KetQuaCongTacCriteria;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import com.mycompany.myapp.service.mapper.KetQuaCongTacMapper;
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
 * Integration tests for the {@link KetQuaCongTacResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class KetQuaCongTacResourceIT {

    private static final String DEFAULT_MA_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_MA_KET_QUA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KET_QUA = "BBBBBBBBBB";

    private static final String DEFAULT_THU_TU_SX = "AAAAAAAAAA";
    private static final String UPDATED_THU_TU_SX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ket-qua-cong-tacs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private KetQuaCongTacRepository ketQuaCongTacRepository;

    @Autowired
    private KetQuaCongTacMapper ketQuaCongTacMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKetQuaCongTacMockMvc;

    private KetQuaCongTac ketQuaCongTac;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KetQuaCongTac createEntity(EntityManager em) {
        KetQuaCongTac ketQuaCongTac = new KetQuaCongTac()
            .maKetQua(DEFAULT_MA_KET_QUA)
            .tenKetQua(DEFAULT_TEN_KET_QUA)
            .thuTuSX(DEFAULT_THU_TU_SX);
        return ketQuaCongTac;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KetQuaCongTac createUpdatedEntity(EntityManager em) {
        KetQuaCongTac ketQuaCongTac = new KetQuaCongTac()
            .maKetQua(UPDATED_MA_KET_QUA)
            .tenKetQua(UPDATED_TEN_KET_QUA)
            .thuTuSX(UPDATED_THU_TU_SX);
        return ketQuaCongTac;
    }

    @BeforeEach
    public void initTest() {
        ketQuaCongTac = createEntity(em);
    }

    @Test
    @Transactional
    void createKetQuaCongTac() throws Exception {
        int databaseSizeBeforeCreate = ketQuaCongTacRepository.findAll().size();
        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);
        restKetQuaCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isCreated());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(DEFAULT_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(DEFAULT_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createKetQuaCongTacWithExistingId() throws Exception {
        // Create the KetQuaCongTac with an existing ID
        ketQuaCongTac.setId(1L);
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        int databaseSizeBeforeCreate = ketQuaCongTacRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restKetQuaCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacs() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ketQuaCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKetQua").value(hasItem(DEFAULT_MA_KET_QUA)))
            .andExpect(jsonPath("$.[*].tenKetQua").value(hasItem(DEFAULT_TEN_KET_QUA)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getKetQuaCongTac() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get the ketQuaCongTac
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL_ID, ketQuaCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ketQuaCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maKetQua").value(DEFAULT_MA_KET_QUA))
            .andExpect(jsonPath("$.tenKetQua").value(DEFAULT_TEN_KET_QUA))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getKetQuaCongTacsByIdFiltering() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        Long id = ketQuaCongTac.getId();

        defaultKetQuaCongTacShouldBeFound("id.equals=" + id);
        defaultKetQuaCongTacShouldNotBeFound("id.notEquals=" + id);

        defaultKetQuaCongTacShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultKetQuaCongTacShouldNotBeFound("id.greaterThan=" + id);

        defaultKetQuaCongTacShouldBeFound("id.lessThanOrEqual=" + id);
        defaultKetQuaCongTacShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByMaKetQuaIsEqualToSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where maKetQua equals to DEFAULT_MA_KET_QUA
        defaultKetQuaCongTacShouldBeFound("maKetQua.equals=" + DEFAULT_MA_KET_QUA);

        // Get all the ketQuaCongTacList where maKetQua equals to UPDATED_MA_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("maKetQua.equals=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByMaKetQuaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where maKetQua not equals to DEFAULT_MA_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("maKetQua.notEquals=" + DEFAULT_MA_KET_QUA);

        // Get all the ketQuaCongTacList where maKetQua not equals to UPDATED_MA_KET_QUA
        defaultKetQuaCongTacShouldBeFound("maKetQua.notEquals=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByMaKetQuaIsInShouldWork() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where maKetQua in DEFAULT_MA_KET_QUA or UPDATED_MA_KET_QUA
        defaultKetQuaCongTacShouldBeFound("maKetQua.in=" + DEFAULT_MA_KET_QUA + "," + UPDATED_MA_KET_QUA);

        // Get all the ketQuaCongTacList where maKetQua equals to UPDATED_MA_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("maKetQua.in=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByMaKetQuaIsNullOrNotNull() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where maKetQua is not null
        defaultKetQuaCongTacShouldBeFound("maKetQua.specified=true");

        // Get all the ketQuaCongTacList where maKetQua is null
        defaultKetQuaCongTacShouldNotBeFound("maKetQua.specified=false");
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByMaKetQuaContainsSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where maKetQua contains DEFAULT_MA_KET_QUA
        defaultKetQuaCongTacShouldBeFound("maKetQua.contains=" + DEFAULT_MA_KET_QUA);

        // Get all the ketQuaCongTacList where maKetQua contains UPDATED_MA_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("maKetQua.contains=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByMaKetQuaNotContainsSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where maKetQua does not contain DEFAULT_MA_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("maKetQua.doesNotContain=" + DEFAULT_MA_KET_QUA);

        // Get all the ketQuaCongTacList where maKetQua does not contain UPDATED_MA_KET_QUA
        defaultKetQuaCongTacShouldBeFound("maKetQua.doesNotContain=" + UPDATED_MA_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByTenKetQuaIsEqualToSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where tenKetQua equals to DEFAULT_TEN_KET_QUA
        defaultKetQuaCongTacShouldBeFound("tenKetQua.equals=" + DEFAULT_TEN_KET_QUA);

        // Get all the ketQuaCongTacList where tenKetQua equals to UPDATED_TEN_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("tenKetQua.equals=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByTenKetQuaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where tenKetQua not equals to DEFAULT_TEN_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("tenKetQua.notEquals=" + DEFAULT_TEN_KET_QUA);

        // Get all the ketQuaCongTacList where tenKetQua not equals to UPDATED_TEN_KET_QUA
        defaultKetQuaCongTacShouldBeFound("tenKetQua.notEquals=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByTenKetQuaIsInShouldWork() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where tenKetQua in DEFAULT_TEN_KET_QUA or UPDATED_TEN_KET_QUA
        defaultKetQuaCongTacShouldBeFound("tenKetQua.in=" + DEFAULT_TEN_KET_QUA + "," + UPDATED_TEN_KET_QUA);

        // Get all the ketQuaCongTacList where tenKetQua equals to UPDATED_TEN_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("tenKetQua.in=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByTenKetQuaIsNullOrNotNull() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where tenKetQua is not null
        defaultKetQuaCongTacShouldBeFound("tenKetQua.specified=true");

        // Get all the ketQuaCongTacList where tenKetQua is null
        defaultKetQuaCongTacShouldNotBeFound("tenKetQua.specified=false");
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByTenKetQuaContainsSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where tenKetQua contains DEFAULT_TEN_KET_QUA
        defaultKetQuaCongTacShouldBeFound("tenKetQua.contains=" + DEFAULT_TEN_KET_QUA);

        // Get all the ketQuaCongTacList where tenKetQua contains UPDATED_TEN_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("tenKetQua.contains=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByTenKetQuaNotContainsSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where tenKetQua does not contain DEFAULT_TEN_KET_QUA
        defaultKetQuaCongTacShouldNotBeFound("tenKetQua.doesNotContain=" + DEFAULT_TEN_KET_QUA);

        // Get all the ketQuaCongTacList where tenKetQua does not contain UPDATED_TEN_KET_QUA
        defaultKetQuaCongTacShouldBeFound("tenKetQua.doesNotContain=" + UPDATED_TEN_KET_QUA);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByThuTuSXIsEqualToSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where thuTuSX equals to DEFAULT_THU_TU_SX
        defaultKetQuaCongTacShouldBeFound("thuTuSX.equals=" + DEFAULT_THU_TU_SX);

        // Get all the ketQuaCongTacList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultKetQuaCongTacShouldNotBeFound("thuTuSX.equals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByThuTuSXIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where thuTuSX not equals to DEFAULT_THU_TU_SX
        defaultKetQuaCongTacShouldNotBeFound("thuTuSX.notEquals=" + DEFAULT_THU_TU_SX);

        // Get all the ketQuaCongTacList where thuTuSX not equals to UPDATED_THU_TU_SX
        defaultKetQuaCongTacShouldBeFound("thuTuSX.notEquals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByThuTuSXIsInShouldWork() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where thuTuSX in DEFAULT_THU_TU_SX or UPDATED_THU_TU_SX
        defaultKetQuaCongTacShouldBeFound("thuTuSX.in=" + DEFAULT_THU_TU_SX + "," + UPDATED_THU_TU_SX);

        // Get all the ketQuaCongTacList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultKetQuaCongTacShouldNotBeFound("thuTuSX.in=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByThuTuSXIsNullOrNotNull() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where thuTuSX is not null
        defaultKetQuaCongTacShouldBeFound("thuTuSX.specified=true");

        // Get all the ketQuaCongTacList where thuTuSX is null
        defaultKetQuaCongTacShouldNotBeFound("thuTuSX.specified=false");
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByThuTuSXContainsSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where thuTuSX contains DEFAULT_THU_TU_SX
        defaultKetQuaCongTacShouldBeFound("thuTuSX.contains=" + DEFAULT_THU_TU_SX);

        // Get all the ketQuaCongTacList where thuTuSX contains UPDATED_THU_TU_SX
        defaultKetQuaCongTacShouldNotBeFound("thuTuSX.contains=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByThuTuSXNotContainsSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList where thuTuSX does not contain DEFAULT_THU_TU_SX
        defaultKetQuaCongTacShouldNotBeFound("thuTuSX.doesNotContain=" + DEFAULT_THU_TU_SX);

        // Get all the ketQuaCongTacList where thuTuSX does not contain UPDATED_THU_TU_SX
        defaultKetQuaCongTacShouldBeFound("thuTuSX.doesNotContain=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacsByChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);
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
        ketQuaCongTac.setChiDaoTuyen(chiDaoTuyen);
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);
        Long chiDaoTuyenId = chiDaoTuyen.getId();

        // Get all the ketQuaCongTacList where chiDaoTuyen equals to chiDaoTuyenId
        defaultKetQuaCongTacShouldBeFound("chiDaoTuyenId.equals=" + chiDaoTuyenId);

        // Get all the ketQuaCongTacList where chiDaoTuyen equals to (chiDaoTuyenId + 1)
        defaultKetQuaCongTacShouldNotBeFound("chiDaoTuyenId.equals=" + (chiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultKetQuaCongTacShouldBeFound(String filter) throws Exception {
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ketQuaCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKetQua").value(hasItem(DEFAULT_MA_KET_QUA)))
            .andExpect(jsonPath("$.[*].tenKetQua").value(hasItem(DEFAULT_TEN_KET_QUA)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));

        // Check, that the count call also returns 1
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultKetQuaCongTacShouldNotBeFound(String filter) throws Exception {
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingKetQuaCongTac() throws Exception {
        // Get the ketQuaCongTac
        restKetQuaCongTacMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewKetQuaCongTac() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();

        // Update the ketQuaCongTac
        KetQuaCongTac updatedKetQuaCongTac = ketQuaCongTacRepository.findById(ketQuaCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedKetQuaCongTac are not directly saved in db
        em.detach(updatedKetQuaCongTac);
        updatedKetQuaCongTac.maKetQua(UPDATED_MA_KET_QUA).tenKetQua(UPDATED_TEN_KET_QUA).thuTuSX(UPDATED_THU_TU_SX);
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(updatedKetQuaCongTac);

        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ketQuaCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isOk());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(UPDATED_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(UPDATED_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ketQuaCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateKetQuaCongTacWithPatch() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();

        // Update the ketQuaCongTac using partial update
        KetQuaCongTac partialUpdatedKetQuaCongTac = new KetQuaCongTac();
        partialUpdatedKetQuaCongTac.setId(ketQuaCongTac.getId());

        partialUpdatedKetQuaCongTac.thuTuSX(UPDATED_THU_TU_SX);

        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKetQuaCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKetQuaCongTac))
            )
            .andExpect(status().isOk());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(DEFAULT_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(DEFAULT_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateKetQuaCongTacWithPatch() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();

        // Update the ketQuaCongTac using partial update
        KetQuaCongTac partialUpdatedKetQuaCongTac = new KetQuaCongTac();
        partialUpdatedKetQuaCongTac.setId(ketQuaCongTac.getId());

        partialUpdatedKetQuaCongTac.maKetQua(UPDATED_MA_KET_QUA).tenKetQua(UPDATED_TEN_KET_QUA).thuTuSX(UPDATED_THU_TU_SX);

        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKetQuaCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKetQuaCongTac))
            )
            .andExpect(status().isOk());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(UPDATED_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(UPDATED_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ketQuaCongTacDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteKetQuaCongTac() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeDelete = ketQuaCongTacRepository.findAll().size();

        // Delete the ketQuaCongTac
        restKetQuaCongTacMockMvc
            .perform(delete(ENTITY_API_URL_ID, ketQuaCongTac.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
