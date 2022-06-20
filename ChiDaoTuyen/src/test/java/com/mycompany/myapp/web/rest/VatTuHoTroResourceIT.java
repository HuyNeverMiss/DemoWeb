package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.repository.VatTuHoTroRepository;
import com.mycompany.myapp.service.criteria.VatTuHoTroCriteria;
import com.mycompany.myapp.service.dto.VatTuHoTroDTO;
import com.mycompany.myapp.service.mapper.VatTuHoTroMapper;
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
 * Integration tests for the {@link VatTuHoTroResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VatTuHoTroResourceIT {

    private static final String DEFAULT_MA_VAT_TU = "AAAAAAAAAA";
    private static final String UPDATED_MA_VAT_TU = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_VAT_TU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_VAT_TU = "BBBBBBBBBB";

    private static final String DEFAULT_THU_TU_SX = "AAAAAAAAAA";
    private static final String UPDATED_THU_TU_SX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/vat-tu-ho-tros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VatTuHoTroRepository vatTuHoTroRepository;

    @Autowired
    private VatTuHoTroMapper vatTuHoTroMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVatTuHoTroMockMvc;

    private VatTuHoTro vatTuHoTro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VatTuHoTro createEntity(EntityManager em) {
        VatTuHoTro vatTuHoTro = new VatTuHoTro().maVatTu(DEFAULT_MA_VAT_TU).tenVatTu(DEFAULT_TEN_VAT_TU).thuTuSX(DEFAULT_THU_TU_SX);
        return vatTuHoTro;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VatTuHoTro createUpdatedEntity(EntityManager em) {
        VatTuHoTro vatTuHoTro = new VatTuHoTro().maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);
        return vatTuHoTro;
    }

    @BeforeEach
    public void initTest() {
        vatTuHoTro = createEntity(em);
    }

    @Test
    @Transactional
    void createVatTuHoTro() throws Exception {
        int databaseSizeBeforeCreate = vatTuHoTroRepository.findAll().size();
        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);
        restVatTuHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeCreate + 1);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(DEFAULT_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(DEFAULT_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createVatTuHoTroWithExistingId() throws Exception {
        // Create the VatTuHoTro with an existing ID
        vatTuHoTro.setId(1L);
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        int databaseSizeBeforeCreate = vatTuHoTroRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVatTuHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVatTuHoTros() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vatTuHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maVatTu").value(hasItem(DEFAULT_MA_VAT_TU)))
            .andExpect(jsonPath("$.[*].tenVatTu").value(hasItem(DEFAULT_TEN_VAT_TU)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getVatTuHoTro() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get the vatTuHoTro
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL_ID, vatTuHoTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vatTuHoTro.getId().intValue()))
            .andExpect(jsonPath("$.maVatTu").value(DEFAULT_MA_VAT_TU))
            .andExpect(jsonPath("$.tenVatTu").value(DEFAULT_TEN_VAT_TU))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getVatTuHoTrosByIdFiltering() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        Long id = vatTuHoTro.getId();

        defaultVatTuHoTroShouldBeFound("id.equals=" + id);
        defaultVatTuHoTroShouldNotBeFound("id.notEquals=" + id);

        defaultVatTuHoTroShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultVatTuHoTroShouldNotBeFound("id.greaterThan=" + id);

        defaultVatTuHoTroShouldBeFound("id.lessThanOrEqual=" + id);
        defaultVatTuHoTroShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByMaVatTuIsEqualToSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where maVatTu equals to DEFAULT_MA_VAT_TU
        defaultVatTuHoTroShouldBeFound("maVatTu.equals=" + DEFAULT_MA_VAT_TU);

        // Get all the vatTuHoTroList where maVatTu equals to UPDATED_MA_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("maVatTu.equals=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByMaVatTuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where maVatTu not equals to DEFAULT_MA_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("maVatTu.notEquals=" + DEFAULT_MA_VAT_TU);

        // Get all the vatTuHoTroList where maVatTu not equals to UPDATED_MA_VAT_TU
        defaultVatTuHoTroShouldBeFound("maVatTu.notEquals=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByMaVatTuIsInShouldWork() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where maVatTu in DEFAULT_MA_VAT_TU or UPDATED_MA_VAT_TU
        defaultVatTuHoTroShouldBeFound("maVatTu.in=" + DEFAULT_MA_VAT_TU + "," + UPDATED_MA_VAT_TU);

        // Get all the vatTuHoTroList where maVatTu equals to UPDATED_MA_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("maVatTu.in=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByMaVatTuIsNullOrNotNull() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where maVatTu is not null
        defaultVatTuHoTroShouldBeFound("maVatTu.specified=true");

        // Get all the vatTuHoTroList where maVatTu is null
        defaultVatTuHoTroShouldNotBeFound("maVatTu.specified=false");
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByMaVatTuContainsSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where maVatTu contains DEFAULT_MA_VAT_TU
        defaultVatTuHoTroShouldBeFound("maVatTu.contains=" + DEFAULT_MA_VAT_TU);

        // Get all the vatTuHoTroList where maVatTu contains UPDATED_MA_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("maVatTu.contains=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByMaVatTuNotContainsSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where maVatTu does not contain DEFAULT_MA_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("maVatTu.doesNotContain=" + DEFAULT_MA_VAT_TU);

        // Get all the vatTuHoTroList where maVatTu does not contain UPDATED_MA_VAT_TU
        defaultVatTuHoTroShouldBeFound("maVatTu.doesNotContain=" + UPDATED_MA_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByTenVatTuIsEqualToSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where tenVatTu equals to DEFAULT_TEN_VAT_TU
        defaultVatTuHoTroShouldBeFound("tenVatTu.equals=" + DEFAULT_TEN_VAT_TU);

        // Get all the vatTuHoTroList where tenVatTu equals to UPDATED_TEN_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("tenVatTu.equals=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByTenVatTuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where tenVatTu not equals to DEFAULT_TEN_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("tenVatTu.notEquals=" + DEFAULT_TEN_VAT_TU);

        // Get all the vatTuHoTroList where tenVatTu not equals to UPDATED_TEN_VAT_TU
        defaultVatTuHoTroShouldBeFound("tenVatTu.notEquals=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByTenVatTuIsInShouldWork() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where tenVatTu in DEFAULT_TEN_VAT_TU or UPDATED_TEN_VAT_TU
        defaultVatTuHoTroShouldBeFound("tenVatTu.in=" + DEFAULT_TEN_VAT_TU + "," + UPDATED_TEN_VAT_TU);

        // Get all the vatTuHoTroList where tenVatTu equals to UPDATED_TEN_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("tenVatTu.in=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByTenVatTuIsNullOrNotNull() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where tenVatTu is not null
        defaultVatTuHoTroShouldBeFound("tenVatTu.specified=true");

        // Get all the vatTuHoTroList where tenVatTu is null
        defaultVatTuHoTroShouldNotBeFound("tenVatTu.specified=false");
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByTenVatTuContainsSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where tenVatTu contains DEFAULT_TEN_VAT_TU
        defaultVatTuHoTroShouldBeFound("tenVatTu.contains=" + DEFAULT_TEN_VAT_TU);

        // Get all the vatTuHoTroList where tenVatTu contains UPDATED_TEN_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("tenVatTu.contains=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByTenVatTuNotContainsSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where tenVatTu does not contain DEFAULT_TEN_VAT_TU
        defaultVatTuHoTroShouldNotBeFound("tenVatTu.doesNotContain=" + DEFAULT_TEN_VAT_TU);

        // Get all the vatTuHoTroList where tenVatTu does not contain UPDATED_TEN_VAT_TU
        defaultVatTuHoTroShouldBeFound("tenVatTu.doesNotContain=" + UPDATED_TEN_VAT_TU);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByThuTuSXIsEqualToSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where thuTuSX equals to DEFAULT_THU_TU_SX
        defaultVatTuHoTroShouldBeFound("thuTuSX.equals=" + DEFAULT_THU_TU_SX);

        // Get all the vatTuHoTroList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultVatTuHoTroShouldNotBeFound("thuTuSX.equals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByThuTuSXIsNotEqualToSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where thuTuSX not equals to DEFAULT_THU_TU_SX
        defaultVatTuHoTroShouldNotBeFound("thuTuSX.notEquals=" + DEFAULT_THU_TU_SX);

        // Get all the vatTuHoTroList where thuTuSX not equals to UPDATED_THU_TU_SX
        defaultVatTuHoTroShouldBeFound("thuTuSX.notEquals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByThuTuSXIsInShouldWork() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where thuTuSX in DEFAULT_THU_TU_SX or UPDATED_THU_TU_SX
        defaultVatTuHoTroShouldBeFound("thuTuSX.in=" + DEFAULT_THU_TU_SX + "," + UPDATED_THU_TU_SX);

        // Get all the vatTuHoTroList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultVatTuHoTroShouldNotBeFound("thuTuSX.in=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByThuTuSXIsNullOrNotNull() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where thuTuSX is not null
        defaultVatTuHoTroShouldBeFound("thuTuSX.specified=true");

        // Get all the vatTuHoTroList where thuTuSX is null
        defaultVatTuHoTroShouldNotBeFound("thuTuSX.specified=false");
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByThuTuSXContainsSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where thuTuSX contains DEFAULT_THU_TU_SX
        defaultVatTuHoTroShouldBeFound("thuTuSX.contains=" + DEFAULT_THU_TU_SX);

        // Get all the vatTuHoTroList where thuTuSX contains UPDATED_THU_TU_SX
        defaultVatTuHoTroShouldNotBeFound("thuTuSX.contains=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByThuTuSXNotContainsSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList where thuTuSX does not contain DEFAULT_THU_TU_SX
        defaultVatTuHoTroShouldNotBeFound("thuTuSX.doesNotContain=" + DEFAULT_THU_TU_SX);

        // Get all the vatTuHoTroList where thuTuSX does not contain UPDATED_THU_TU_SX
        defaultVatTuHoTroShouldBeFound("thuTuSX.doesNotContain=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllVatTuHoTrosByChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);
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
        vatTuHoTro.setChiDaoTuyen(chiDaoTuyen);
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);
        Long chiDaoTuyenId = chiDaoTuyen.getId();

        // Get all the vatTuHoTroList where chiDaoTuyen equals to chiDaoTuyenId
        defaultVatTuHoTroShouldBeFound("chiDaoTuyenId.equals=" + chiDaoTuyenId);

        // Get all the vatTuHoTroList where chiDaoTuyen equals to (chiDaoTuyenId + 1)
        defaultVatTuHoTroShouldNotBeFound("chiDaoTuyenId.equals=" + (chiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultVatTuHoTroShouldBeFound(String filter) throws Exception {
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vatTuHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maVatTu").value(hasItem(DEFAULT_MA_VAT_TU)))
            .andExpect(jsonPath("$.[*].tenVatTu").value(hasItem(DEFAULT_TEN_VAT_TU)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));

        // Check, that the count call also returns 1
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultVatTuHoTroShouldNotBeFound(String filter) throws Exception {
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingVatTuHoTro() throws Exception {
        // Get the vatTuHoTro
        restVatTuHoTroMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVatTuHoTro() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();

        // Update the vatTuHoTro
        VatTuHoTro updatedVatTuHoTro = vatTuHoTroRepository.findById(vatTuHoTro.getId()).get();
        // Disconnect from session so that the updates on updatedVatTuHoTro are not directly saved in db
        em.detach(updatedVatTuHoTro);
        updatedVatTuHoTro.maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(updatedVatTuHoTro);

        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vatTuHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isOk());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(UPDATED_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(UPDATED_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vatTuHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVatTuHoTroWithPatch() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();

        // Update the vatTuHoTro using partial update
        VatTuHoTro partialUpdatedVatTuHoTro = new VatTuHoTro();
        partialUpdatedVatTuHoTro.setId(vatTuHoTro.getId());

        partialUpdatedVatTuHoTro.maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);

        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVatTuHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVatTuHoTro))
            )
            .andExpect(status().isOk());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(UPDATED_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(UPDATED_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateVatTuHoTroWithPatch() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();

        // Update the vatTuHoTro using partial update
        VatTuHoTro partialUpdatedVatTuHoTro = new VatTuHoTro();
        partialUpdatedVatTuHoTro.setId(vatTuHoTro.getId());

        partialUpdatedVatTuHoTro.maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);

        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVatTuHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVatTuHoTro))
            )
            .andExpect(status().isOk());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(UPDATED_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(UPDATED_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vatTuHoTroDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVatTuHoTro() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeDelete = vatTuHoTroRepository.findAll().size();

        // Delete the vatTuHoTro
        restVatTuHoTroMockMvc
            .perform(delete(ENTITY_API_URL_ID, vatTuHoTro.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
