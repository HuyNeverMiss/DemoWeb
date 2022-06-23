package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.repository.KyThuatHoTroRepository;
import com.mycompany.myapp.service.criteria.KyThuatHoTroCriteria;
import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
import com.mycompany.myapp.service.mapper.KyThuatHoTroMapper;
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
 * Integration tests for the {@link KyThuatHoTroResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class KyThuatHoTroResourceIT {

    private static final String DEFAULT_MA_KY_THUAT = "AAAAAAAAAA";
    private static final String UPDATED_MA_KY_THUAT = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_KY_THUAT = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KY_THUAT = "BBBBBBBBBB";

    private static final String DEFAULT_THU_TU_SX = "AAAAAAAAAA";
    private static final String UPDATED_THU_TU_SX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ky-thuat-ho-tros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private KyThuatHoTroRepository kyThuatHoTroRepository;

    @Autowired
    private KyThuatHoTroMapper kyThuatHoTroMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKyThuatHoTroMockMvc;

    private KyThuatHoTro kyThuatHoTro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KyThuatHoTro createEntity(EntityManager em) {
        KyThuatHoTro kyThuatHoTro = new KyThuatHoTro()
            .maKyThuat(DEFAULT_MA_KY_THUAT)
            .tenKyThuat(DEFAULT_TEN_KY_THUAT)
            .thuTuSX(DEFAULT_THU_TU_SX);
        return kyThuatHoTro;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KyThuatHoTro createUpdatedEntity(EntityManager em) {
        KyThuatHoTro kyThuatHoTro = new KyThuatHoTro()
            .maKyThuat(UPDATED_MA_KY_THUAT)
            .tenKyThuat(UPDATED_TEN_KY_THUAT)
            .thuTuSX(UPDATED_THU_TU_SX);
        return kyThuatHoTro;
    }

    @BeforeEach
    public void initTest() {
        kyThuatHoTro = createEntity(em);
    }

    @Test
    @Transactional
    void createKyThuatHoTro() throws Exception {
        int databaseSizeBeforeCreate = kyThuatHoTroRepository.findAll().size();
        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);
        restKyThuatHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isCreated());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeCreate + 1);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(DEFAULT_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(DEFAULT_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createKyThuatHoTroWithExistingId() throws Exception {
        // Create the KyThuatHoTro with an existing ID
        kyThuatHoTro.setId(1L);
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        int databaseSizeBeforeCreate = kyThuatHoTroRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restKyThuatHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMaKyThuatIsRequired() throws Exception {
        int databaseSizeBeforeTest = kyThuatHoTroRepository.findAll().size();
        // set the field null
        kyThuatHoTro.setMaKyThuat(null);

        // Create the KyThuatHoTro, which fails.
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        restKyThuatHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTenKyThuatIsRequired() throws Exception {
        int databaseSizeBeforeTest = kyThuatHoTroRepository.findAll().size();
        // set the field null
        kyThuatHoTro.setTenKyThuat(null);

        // Create the KyThuatHoTro, which fails.
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        restKyThuatHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTros() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kyThuatHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKyThuat").value(hasItem(DEFAULT_MA_KY_THUAT)))
            .andExpect(jsonPath("$.[*].tenKyThuat").value(hasItem(DEFAULT_TEN_KY_THUAT)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getKyThuatHoTro() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get the kyThuatHoTro
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL_ID, kyThuatHoTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kyThuatHoTro.getId().intValue()))
            .andExpect(jsonPath("$.maKyThuat").value(DEFAULT_MA_KY_THUAT))
            .andExpect(jsonPath("$.tenKyThuat").value(DEFAULT_TEN_KY_THUAT))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getKyThuatHoTrosByIdFiltering() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        Long id = kyThuatHoTro.getId();

        defaultKyThuatHoTroShouldBeFound("id.equals=" + id);
        defaultKyThuatHoTroShouldNotBeFound("id.notEquals=" + id);

        defaultKyThuatHoTroShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultKyThuatHoTroShouldNotBeFound("id.greaterThan=" + id);

        defaultKyThuatHoTroShouldBeFound("id.lessThanOrEqual=" + id);
        defaultKyThuatHoTroShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByMaKyThuatIsEqualToSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where maKyThuat equals to DEFAULT_MA_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("maKyThuat.equals=" + DEFAULT_MA_KY_THUAT);

        // Get all the kyThuatHoTroList where maKyThuat equals to UPDATED_MA_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("maKyThuat.equals=" + UPDATED_MA_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByMaKyThuatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where maKyThuat not equals to DEFAULT_MA_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("maKyThuat.notEquals=" + DEFAULT_MA_KY_THUAT);

        // Get all the kyThuatHoTroList where maKyThuat not equals to UPDATED_MA_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("maKyThuat.notEquals=" + UPDATED_MA_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByMaKyThuatIsInShouldWork() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where maKyThuat in DEFAULT_MA_KY_THUAT or UPDATED_MA_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("maKyThuat.in=" + DEFAULT_MA_KY_THUAT + "," + UPDATED_MA_KY_THUAT);

        // Get all the kyThuatHoTroList where maKyThuat equals to UPDATED_MA_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("maKyThuat.in=" + UPDATED_MA_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByMaKyThuatIsNullOrNotNull() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where maKyThuat is not null
        defaultKyThuatHoTroShouldBeFound("maKyThuat.specified=true");

        // Get all the kyThuatHoTroList where maKyThuat is null
        defaultKyThuatHoTroShouldNotBeFound("maKyThuat.specified=false");
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByMaKyThuatContainsSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where maKyThuat contains DEFAULT_MA_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("maKyThuat.contains=" + DEFAULT_MA_KY_THUAT);

        // Get all the kyThuatHoTroList where maKyThuat contains UPDATED_MA_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("maKyThuat.contains=" + UPDATED_MA_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByMaKyThuatNotContainsSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where maKyThuat does not contain DEFAULT_MA_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("maKyThuat.doesNotContain=" + DEFAULT_MA_KY_THUAT);

        // Get all the kyThuatHoTroList where maKyThuat does not contain UPDATED_MA_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("maKyThuat.doesNotContain=" + UPDATED_MA_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByTenKyThuatIsEqualToSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where tenKyThuat equals to DEFAULT_TEN_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("tenKyThuat.equals=" + DEFAULT_TEN_KY_THUAT);

        // Get all the kyThuatHoTroList where tenKyThuat equals to UPDATED_TEN_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("tenKyThuat.equals=" + UPDATED_TEN_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByTenKyThuatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where tenKyThuat not equals to DEFAULT_TEN_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("tenKyThuat.notEquals=" + DEFAULT_TEN_KY_THUAT);

        // Get all the kyThuatHoTroList where tenKyThuat not equals to UPDATED_TEN_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("tenKyThuat.notEquals=" + UPDATED_TEN_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByTenKyThuatIsInShouldWork() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where tenKyThuat in DEFAULT_TEN_KY_THUAT or UPDATED_TEN_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("tenKyThuat.in=" + DEFAULT_TEN_KY_THUAT + "," + UPDATED_TEN_KY_THUAT);

        // Get all the kyThuatHoTroList where tenKyThuat equals to UPDATED_TEN_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("tenKyThuat.in=" + UPDATED_TEN_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByTenKyThuatIsNullOrNotNull() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where tenKyThuat is not null
        defaultKyThuatHoTroShouldBeFound("tenKyThuat.specified=true");

        // Get all the kyThuatHoTroList where tenKyThuat is null
        defaultKyThuatHoTroShouldNotBeFound("tenKyThuat.specified=false");
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByTenKyThuatContainsSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where tenKyThuat contains DEFAULT_TEN_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("tenKyThuat.contains=" + DEFAULT_TEN_KY_THUAT);

        // Get all the kyThuatHoTroList where tenKyThuat contains UPDATED_TEN_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("tenKyThuat.contains=" + UPDATED_TEN_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByTenKyThuatNotContainsSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where tenKyThuat does not contain DEFAULT_TEN_KY_THUAT
        defaultKyThuatHoTroShouldNotBeFound("tenKyThuat.doesNotContain=" + DEFAULT_TEN_KY_THUAT);

        // Get all the kyThuatHoTroList where tenKyThuat does not contain UPDATED_TEN_KY_THUAT
        defaultKyThuatHoTroShouldBeFound("tenKyThuat.doesNotContain=" + UPDATED_TEN_KY_THUAT);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByThuTuSXIsEqualToSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where thuTuSX equals to DEFAULT_THU_TU_SX
        defaultKyThuatHoTroShouldBeFound("thuTuSX.equals=" + DEFAULT_THU_TU_SX);

        // Get all the kyThuatHoTroList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultKyThuatHoTroShouldNotBeFound("thuTuSX.equals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByThuTuSXIsNotEqualToSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where thuTuSX not equals to DEFAULT_THU_TU_SX
        defaultKyThuatHoTroShouldNotBeFound("thuTuSX.notEquals=" + DEFAULT_THU_TU_SX);

        // Get all the kyThuatHoTroList where thuTuSX not equals to UPDATED_THU_TU_SX
        defaultKyThuatHoTroShouldBeFound("thuTuSX.notEquals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByThuTuSXIsInShouldWork() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where thuTuSX in DEFAULT_THU_TU_SX or UPDATED_THU_TU_SX
        defaultKyThuatHoTroShouldBeFound("thuTuSX.in=" + DEFAULT_THU_TU_SX + "," + UPDATED_THU_TU_SX);

        // Get all the kyThuatHoTroList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultKyThuatHoTroShouldNotBeFound("thuTuSX.in=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByThuTuSXIsNullOrNotNull() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where thuTuSX is not null
        defaultKyThuatHoTroShouldBeFound("thuTuSX.specified=true");

        // Get all the kyThuatHoTroList where thuTuSX is null
        defaultKyThuatHoTroShouldNotBeFound("thuTuSX.specified=false");
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByThuTuSXContainsSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where thuTuSX contains DEFAULT_THU_TU_SX
        defaultKyThuatHoTroShouldBeFound("thuTuSX.contains=" + DEFAULT_THU_TU_SX);

        // Get all the kyThuatHoTroList where thuTuSX contains UPDATED_THU_TU_SX
        defaultKyThuatHoTroShouldNotBeFound("thuTuSX.contains=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByThuTuSXNotContainsSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList where thuTuSX does not contain DEFAULT_THU_TU_SX
        defaultKyThuatHoTroShouldNotBeFound("thuTuSX.doesNotContain=" + DEFAULT_THU_TU_SX);

        // Get all the kyThuatHoTroList where thuTuSX does not contain UPDATED_THU_TU_SX
        defaultKyThuatHoTroShouldBeFound("thuTuSX.doesNotContain=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTrosByChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);
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
        kyThuatHoTro.addChiDaoTuyen(chiDaoTuyen);
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);
        Long chiDaoTuyenId = chiDaoTuyen.getId();

        // Get all the kyThuatHoTroList where chiDaoTuyen equals to chiDaoTuyenId
        defaultKyThuatHoTroShouldBeFound("chiDaoTuyenId.equals=" + chiDaoTuyenId);

        // Get all the kyThuatHoTroList where chiDaoTuyen equals to (chiDaoTuyenId + 1)
        defaultKyThuatHoTroShouldNotBeFound("chiDaoTuyenId.equals=" + (chiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultKyThuatHoTroShouldBeFound(String filter) throws Exception {
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kyThuatHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKyThuat").value(hasItem(DEFAULT_MA_KY_THUAT)))
            .andExpect(jsonPath("$.[*].tenKyThuat").value(hasItem(DEFAULT_TEN_KY_THUAT)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));

        // Check, that the count call also returns 1
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultKyThuatHoTroShouldNotBeFound(String filter) throws Exception {
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingKyThuatHoTro() throws Exception {
        // Get the kyThuatHoTro
        restKyThuatHoTroMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewKyThuatHoTro() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();

        // Update the kyThuatHoTro
        KyThuatHoTro updatedKyThuatHoTro = kyThuatHoTroRepository.findById(kyThuatHoTro.getId()).get();
        // Disconnect from session so that the updates on updatedKyThuatHoTro are not directly saved in db
        em.detach(updatedKyThuatHoTro);
        updatedKyThuatHoTro.maKyThuat(UPDATED_MA_KY_THUAT).tenKyThuat(UPDATED_TEN_KY_THUAT).thuTuSX(UPDATED_THU_TU_SX);
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(updatedKyThuatHoTro);

        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, kyThuatHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isOk());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(UPDATED_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(UPDATED_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, kyThuatHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateKyThuatHoTroWithPatch() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();

        // Update the kyThuatHoTro using partial update
        KyThuatHoTro partialUpdatedKyThuatHoTro = new KyThuatHoTro();
        partialUpdatedKyThuatHoTro.setId(kyThuatHoTro.getId());

        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKyThuatHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKyThuatHoTro))
            )
            .andExpect(status().isOk());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(DEFAULT_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(DEFAULT_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateKyThuatHoTroWithPatch() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();

        // Update the kyThuatHoTro using partial update
        KyThuatHoTro partialUpdatedKyThuatHoTro = new KyThuatHoTro();
        partialUpdatedKyThuatHoTro.setId(kyThuatHoTro.getId());

        partialUpdatedKyThuatHoTro.maKyThuat(UPDATED_MA_KY_THUAT).tenKyThuat(UPDATED_TEN_KY_THUAT).thuTuSX(UPDATED_THU_TU_SX);

        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKyThuatHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKyThuatHoTro))
            )
            .andExpect(status().isOk());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(UPDATED_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(UPDATED_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, kyThuatHoTroDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteKyThuatHoTro() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeDelete = kyThuatHoTroRepository.findAll().size();

        // Delete the kyThuatHoTro
        restKyThuatHoTroMockMvc
            .perform(delete(ENTITY_API_URL_ID, kyThuatHoTro.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
