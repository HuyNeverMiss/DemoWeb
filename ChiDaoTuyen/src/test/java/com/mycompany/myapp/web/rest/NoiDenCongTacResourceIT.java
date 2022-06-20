package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.repository.NoiDenCongTacRepository;
import com.mycompany.myapp.service.criteria.NoiDenCongTacCriteria;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import com.mycompany.myapp.service.mapper.NoiDenCongTacMapper;
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
 * Integration tests for the {@link NoiDenCongTacResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoiDenCongTacResourceIT {

    private static final String DEFAULT_MA_NOI_DEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_NOI_DEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_NOI_DEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NOI_DEN = "BBBBBBBBBB";

    private static final String DEFAULT_THU_TU_SX = "AAAAAAAAAA";
    private static final String UPDATED_THU_TU_SX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/noi-den-cong-tacs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NoiDenCongTacRepository noiDenCongTacRepository;

    @Autowired
    private NoiDenCongTacMapper noiDenCongTacMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoiDenCongTacMockMvc;

    private NoiDenCongTac noiDenCongTac;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoiDenCongTac createEntity(EntityManager em) {
        NoiDenCongTac noiDenCongTac = new NoiDenCongTac()
            .maNoiDen(DEFAULT_MA_NOI_DEN)
            .tenNoiDen(DEFAULT_TEN_NOI_DEN)
            .thuTuSX(DEFAULT_THU_TU_SX);
        return noiDenCongTac;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoiDenCongTac createUpdatedEntity(EntityManager em) {
        NoiDenCongTac noiDenCongTac = new NoiDenCongTac()
            .maNoiDen(UPDATED_MA_NOI_DEN)
            .tenNoiDen(UPDATED_TEN_NOI_DEN)
            .thuTuSX(UPDATED_THU_TU_SX);
        return noiDenCongTac;
    }

    @BeforeEach
    public void initTest() {
        noiDenCongTac = createEntity(em);
    }

    @Test
    @Transactional
    void createNoiDenCongTac() throws Exception {
        int databaseSizeBeforeCreate = noiDenCongTacRepository.findAll().size();
        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);
        restNoiDenCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(DEFAULT_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(DEFAULT_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createNoiDenCongTacWithExistingId() throws Exception {
        // Create the NoiDenCongTac with an existing ID
        noiDenCongTac.setId(1L);
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        int databaseSizeBeforeCreate = noiDenCongTacRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoiDenCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacs() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noiDenCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDen").value(hasItem(DEFAULT_MA_NOI_DEN)))
            .andExpect(jsonPath("$.[*].tenNoiDen").value(hasItem(DEFAULT_TEN_NOI_DEN)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getNoiDenCongTac() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get the noiDenCongTac
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL_ID, noiDenCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(noiDenCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maNoiDen").value(DEFAULT_MA_NOI_DEN))
            .andExpect(jsonPath("$.tenNoiDen").value(DEFAULT_TEN_NOI_DEN))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getNoiDenCongTacsByIdFiltering() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        Long id = noiDenCongTac.getId();

        defaultNoiDenCongTacShouldBeFound("id.equals=" + id);
        defaultNoiDenCongTacShouldNotBeFound("id.notEquals=" + id);

        defaultNoiDenCongTacShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNoiDenCongTacShouldNotBeFound("id.greaterThan=" + id);

        defaultNoiDenCongTacShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNoiDenCongTacShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByMaNoiDenIsEqualToSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where maNoiDen equals to DEFAULT_MA_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("maNoiDen.equals=" + DEFAULT_MA_NOI_DEN);

        // Get all the noiDenCongTacList where maNoiDen equals to UPDATED_MA_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("maNoiDen.equals=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByMaNoiDenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where maNoiDen not equals to DEFAULT_MA_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("maNoiDen.notEquals=" + DEFAULT_MA_NOI_DEN);

        // Get all the noiDenCongTacList where maNoiDen not equals to UPDATED_MA_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("maNoiDen.notEquals=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByMaNoiDenIsInShouldWork() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where maNoiDen in DEFAULT_MA_NOI_DEN or UPDATED_MA_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("maNoiDen.in=" + DEFAULT_MA_NOI_DEN + "," + UPDATED_MA_NOI_DEN);

        // Get all the noiDenCongTacList where maNoiDen equals to UPDATED_MA_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("maNoiDen.in=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByMaNoiDenIsNullOrNotNull() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where maNoiDen is not null
        defaultNoiDenCongTacShouldBeFound("maNoiDen.specified=true");

        // Get all the noiDenCongTacList where maNoiDen is null
        defaultNoiDenCongTacShouldNotBeFound("maNoiDen.specified=false");
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByMaNoiDenContainsSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where maNoiDen contains DEFAULT_MA_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("maNoiDen.contains=" + DEFAULT_MA_NOI_DEN);

        // Get all the noiDenCongTacList where maNoiDen contains UPDATED_MA_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("maNoiDen.contains=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByMaNoiDenNotContainsSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where maNoiDen does not contain DEFAULT_MA_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("maNoiDen.doesNotContain=" + DEFAULT_MA_NOI_DEN);

        // Get all the noiDenCongTacList where maNoiDen does not contain UPDATED_MA_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("maNoiDen.doesNotContain=" + UPDATED_MA_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByTenNoiDenIsEqualToSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where tenNoiDen equals to DEFAULT_TEN_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("tenNoiDen.equals=" + DEFAULT_TEN_NOI_DEN);

        // Get all the noiDenCongTacList where tenNoiDen equals to UPDATED_TEN_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("tenNoiDen.equals=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByTenNoiDenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where tenNoiDen not equals to DEFAULT_TEN_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("tenNoiDen.notEquals=" + DEFAULT_TEN_NOI_DEN);

        // Get all the noiDenCongTacList where tenNoiDen not equals to UPDATED_TEN_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("tenNoiDen.notEquals=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByTenNoiDenIsInShouldWork() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where tenNoiDen in DEFAULT_TEN_NOI_DEN or UPDATED_TEN_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("tenNoiDen.in=" + DEFAULT_TEN_NOI_DEN + "," + UPDATED_TEN_NOI_DEN);

        // Get all the noiDenCongTacList where tenNoiDen equals to UPDATED_TEN_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("tenNoiDen.in=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByTenNoiDenIsNullOrNotNull() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where tenNoiDen is not null
        defaultNoiDenCongTacShouldBeFound("tenNoiDen.specified=true");

        // Get all the noiDenCongTacList where tenNoiDen is null
        defaultNoiDenCongTacShouldNotBeFound("tenNoiDen.specified=false");
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByTenNoiDenContainsSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where tenNoiDen contains DEFAULT_TEN_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("tenNoiDen.contains=" + DEFAULT_TEN_NOI_DEN);

        // Get all the noiDenCongTacList where tenNoiDen contains UPDATED_TEN_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("tenNoiDen.contains=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByTenNoiDenNotContainsSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where tenNoiDen does not contain DEFAULT_TEN_NOI_DEN
        defaultNoiDenCongTacShouldNotBeFound("tenNoiDen.doesNotContain=" + DEFAULT_TEN_NOI_DEN);

        // Get all the noiDenCongTacList where tenNoiDen does not contain UPDATED_TEN_NOI_DEN
        defaultNoiDenCongTacShouldBeFound("tenNoiDen.doesNotContain=" + UPDATED_TEN_NOI_DEN);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByThuTuSXIsEqualToSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where thuTuSX equals to DEFAULT_THU_TU_SX
        defaultNoiDenCongTacShouldBeFound("thuTuSX.equals=" + DEFAULT_THU_TU_SX);

        // Get all the noiDenCongTacList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultNoiDenCongTacShouldNotBeFound("thuTuSX.equals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByThuTuSXIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where thuTuSX not equals to DEFAULT_THU_TU_SX
        defaultNoiDenCongTacShouldNotBeFound("thuTuSX.notEquals=" + DEFAULT_THU_TU_SX);

        // Get all the noiDenCongTacList where thuTuSX not equals to UPDATED_THU_TU_SX
        defaultNoiDenCongTacShouldBeFound("thuTuSX.notEquals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByThuTuSXIsInShouldWork() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where thuTuSX in DEFAULT_THU_TU_SX or UPDATED_THU_TU_SX
        defaultNoiDenCongTacShouldBeFound("thuTuSX.in=" + DEFAULT_THU_TU_SX + "," + UPDATED_THU_TU_SX);

        // Get all the noiDenCongTacList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultNoiDenCongTacShouldNotBeFound("thuTuSX.in=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByThuTuSXIsNullOrNotNull() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where thuTuSX is not null
        defaultNoiDenCongTacShouldBeFound("thuTuSX.specified=true");

        // Get all the noiDenCongTacList where thuTuSX is null
        defaultNoiDenCongTacShouldNotBeFound("thuTuSX.specified=false");
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByThuTuSXContainsSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where thuTuSX contains DEFAULT_THU_TU_SX
        defaultNoiDenCongTacShouldBeFound("thuTuSX.contains=" + DEFAULT_THU_TU_SX);

        // Get all the noiDenCongTacList where thuTuSX contains UPDATED_THU_TU_SX
        defaultNoiDenCongTacShouldNotBeFound("thuTuSX.contains=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByThuTuSXNotContainsSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList where thuTuSX does not contain DEFAULT_THU_TU_SX
        defaultNoiDenCongTacShouldNotBeFound("thuTuSX.doesNotContain=" + DEFAULT_THU_TU_SX);

        // Get all the noiDenCongTacList where thuTuSX does not contain UPDATED_THU_TU_SX
        defaultNoiDenCongTacShouldBeFound("thuTuSX.doesNotContain=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacsByChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);
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
        noiDenCongTac.setChiDaoTuyen(chiDaoTuyen);
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);
        Long chiDaoTuyenId = chiDaoTuyen.getId();

        // Get all the noiDenCongTacList where chiDaoTuyen equals to chiDaoTuyenId
        defaultNoiDenCongTacShouldBeFound("chiDaoTuyenId.equals=" + chiDaoTuyenId);

        // Get all the noiDenCongTacList where chiDaoTuyen equals to (chiDaoTuyenId + 1)
        defaultNoiDenCongTacShouldNotBeFound("chiDaoTuyenId.equals=" + (chiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNoiDenCongTacShouldBeFound(String filter) throws Exception {
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noiDenCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDen").value(hasItem(DEFAULT_MA_NOI_DEN)))
            .andExpect(jsonPath("$.[*].tenNoiDen").value(hasItem(DEFAULT_TEN_NOI_DEN)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));

        // Check, that the count call also returns 1
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNoiDenCongTacShouldNotBeFound(String filter) throws Exception {
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingNoiDenCongTac() throws Exception {
        // Get the noiDenCongTac
        restNoiDenCongTacMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNoiDenCongTac() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();

        // Update the noiDenCongTac
        NoiDenCongTac updatedNoiDenCongTac = noiDenCongTacRepository.findById(noiDenCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedNoiDenCongTac are not directly saved in db
        em.detach(updatedNoiDenCongTac);
        updatedNoiDenCongTac.maNoiDen(UPDATED_MA_NOI_DEN).tenNoiDen(UPDATED_TEN_NOI_DEN).thuTuSX(UPDATED_THU_TU_SX);
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(updatedNoiDenCongTac);

        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noiDenCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(UPDATED_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(UPDATED_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noiDenCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoiDenCongTacWithPatch() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();

        // Update the noiDenCongTac using partial update
        NoiDenCongTac partialUpdatedNoiDenCongTac = new NoiDenCongTac();
        partialUpdatedNoiDenCongTac.setId(noiDenCongTac.getId());

        partialUpdatedNoiDenCongTac.thuTuSX(UPDATED_THU_TU_SX);

        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoiDenCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoiDenCongTac))
            )
            .andExpect(status().isOk());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(DEFAULT_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(DEFAULT_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateNoiDenCongTacWithPatch() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();

        // Update the noiDenCongTac using partial update
        NoiDenCongTac partialUpdatedNoiDenCongTac = new NoiDenCongTac();
        partialUpdatedNoiDenCongTac.setId(noiDenCongTac.getId());

        partialUpdatedNoiDenCongTac.maNoiDen(UPDATED_MA_NOI_DEN).tenNoiDen(UPDATED_TEN_NOI_DEN).thuTuSX(UPDATED_THU_TU_SX);

        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoiDenCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoiDenCongTac))
            )
            .andExpect(status().isOk());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(UPDATED_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(UPDATED_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noiDenCongTacDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoiDenCongTac() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeDelete = noiDenCongTacRepository.findAll().size();

        // Delete the noiDenCongTac
        restNoiDenCongTacMockMvc
            .perform(delete(ENTITY_API_URL_ID, noiDenCongTac.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
