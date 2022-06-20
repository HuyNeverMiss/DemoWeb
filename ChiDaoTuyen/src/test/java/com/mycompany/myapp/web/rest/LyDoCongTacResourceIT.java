package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.repository.LyDoCongTacRepository;
import com.mycompany.myapp.service.criteria.LyDoCongTacCriteria;
import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import com.mycompany.myapp.service.mapper.LyDoCongTacMapper;
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
 * Integration tests for the {@link LyDoCongTacResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LyDoCongTacResourceIT {

    private static final String DEFAULT_MA_LY_DO = "AAAAAAAAAA";
    private static final String UPDATED_MA_LY_DO = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_LY_DO = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LY_DO = "BBBBBBBBBB";

    private static final String DEFAULT_THU_TU_SX = "AAAAAAAAAA";
    private static final String UPDATED_THU_TU_SX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ly-do-cong-tacs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LyDoCongTacRepository lyDoCongTacRepository;

    @Autowired
    private LyDoCongTacMapper lyDoCongTacMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLyDoCongTacMockMvc;

    private LyDoCongTac lyDoCongTac;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LyDoCongTac createEntity(EntityManager em) {
        LyDoCongTac lyDoCongTac = new LyDoCongTac().maLyDo(DEFAULT_MA_LY_DO).tenLyDo(DEFAULT_TEN_LY_DO).thuTuSX(DEFAULT_THU_TU_SX);
        return lyDoCongTac;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LyDoCongTac createUpdatedEntity(EntityManager em) {
        LyDoCongTac lyDoCongTac = new LyDoCongTac().maLyDo(UPDATED_MA_LY_DO).tenLyDo(UPDATED_TEN_LY_DO).thuTuSX(UPDATED_THU_TU_SX);
        return lyDoCongTac;
    }

    @BeforeEach
    public void initTest() {
        lyDoCongTac = createEntity(em);
    }

    @Test
    @Transactional
    void createLyDoCongTac() throws Exception {
        int databaseSizeBeforeCreate = lyDoCongTacRepository.findAll().size();
        // Create the LyDoCongTac
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);
        restLyDoCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isCreated());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        LyDoCongTac testLyDoCongTac = lyDoCongTacList.get(lyDoCongTacList.size() - 1);
        assertThat(testLyDoCongTac.getMaLyDo()).isEqualTo(DEFAULT_MA_LY_DO);
        assertThat(testLyDoCongTac.getTenLyDo()).isEqualTo(DEFAULT_TEN_LY_DO);
        assertThat(testLyDoCongTac.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createLyDoCongTacWithExistingId() throws Exception {
        // Create the LyDoCongTac with an existing ID
        lyDoCongTac.setId(1L);
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);

        int databaseSizeBeforeCreate = lyDoCongTacRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLyDoCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacs() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList
        restLyDoCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lyDoCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLyDo").value(hasItem(DEFAULT_MA_LY_DO)))
            .andExpect(jsonPath("$.[*].tenLyDo").value(hasItem(DEFAULT_TEN_LY_DO)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getLyDoCongTac() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get the lyDoCongTac
        restLyDoCongTacMockMvc
            .perform(get(ENTITY_API_URL_ID, lyDoCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lyDoCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maLyDo").value(DEFAULT_MA_LY_DO))
            .andExpect(jsonPath("$.tenLyDo").value(DEFAULT_TEN_LY_DO))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getLyDoCongTacsByIdFiltering() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        Long id = lyDoCongTac.getId();

        defaultLyDoCongTacShouldBeFound("id.equals=" + id);
        defaultLyDoCongTacShouldNotBeFound("id.notEquals=" + id);

        defaultLyDoCongTacShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLyDoCongTacShouldNotBeFound("id.greaterThan=" + id);

        defaultLyDoCongTacShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLyDoCongTacShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByMaLyDoIsEqualToSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where maLyDo equals to DEFAULT_MA_LY_DO
        defaultLyDoCongTacShouldBeFound("maLyDo.equals=" + DEFAULT_MA_LY_DO);

        // Get all the lyDoCongTacList where maLyDo equals to UPDATED_MA_LY_DO
        defaultLyDoCongTacShouldNotBeFound("maLyDo.equals=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByMaLyDoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where maLyDo not equals to DEFAULT_MA_LY_DO
        defaultLyDoCongTacShouldNotBeFound("maLyDo.notEquals=" + DEFAULT_MA_LY_DO);

        // Get all the lyDoCongTacList where maLyDo not equals to UPDATED_MA_LY_DO
        defaultLyDoCongTacShouldBeFound("maLyDo.notEquals=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByMaLyDoIsInShouldWork() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where maLyDo in DEFAULT_MA_LY_DO or UPDATED_MA_LY_DO
        defaultLyDoCongTacShouldBeFound("maLyDo.in=" + DEFAULT_MA_LY_DO + "," + UPDATED_MA_LY_DO);

        // Get all the lyDoCongTacList where maLyDo equals to UPDATED_MA_LY_DO
        defaultLyDoCongTacShouldNotBeFound("maLyDo.in=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByMaLyDoIsNullOrNotNull() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where maLyDo is not null
        defaultLyDoCongTacShouldBeFound("maLyDo.specified=true");

        // Get all the lyDoCongTacList where maLyDo is null
        defaultLyDoCongTacShouldNotBeFound("maLyDo.specified=false");
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByMaLyDoContainsSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where maLyDo contains DEFAULT_MA_LY_DO
        defaultLyDoCongTacShouldBeFound("maLyDo.contains=" + DEFAULT_MA_LY_DO);

        // Get all the lyDoCongTacList where maLyDo contains UPDATED_MA_LY_DO
        defaultLyDoCongTacShouldNotBeFound("maLyDo.contains=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByMaLyDoNotContainsSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where maLyDo does not contain DEFAULT_MA_LY_DO
        defaultLyDoCongTacShouldNotBeFound("maLyDo.doesNotContain=" + DEFAULT_MA_LY_DO);

        // Get all the lyDoCongTacList where maLyDo does not contain UPDATED_MA_LY_DO
        defaultLyDoCongTacShouldBeFound("maLyDo.doesNotContain=" + UPDATED_MA_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByTenLyDoIsEqualToSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where tenLyDo equals to DEFAULT_TEN_LY_DO
        defaultLyDoCongTacShouldBeFound("tenLyDo.equals=" + DEFAULT_TEN_LY_DO);

        // Get all the lyDoCongTacList where tenLyDo equals to UPDATED_TEN_LY_DO
        defaultLyDoCongTacShouldNotBeFound("tenLyDo.equals=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByTenLyDoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where tenLyDo not equals to DEFAULT_TEN_LY_DO
        defaultLyDoCongTacShouldNotBeFound("tenLyDo.notEquals=" + DEFAULT_TEN_LY_DO);

        // Get all the lyDoCongTacList where tenLyDo not equals to UPDATED_TEN_LY_DO
        defaultLyDoCongTacShouldBeFound("tenLyDo.notEquals=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByTenLyDoIsInShouldWork() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where tenLyDo in DEFAULT_TEN_LY_DO or UPDATED_TEN_LY_DO
        defaultLyDoCongTacShouldBeFound("tenLyDo.in=" + DEFAULT_TEN_LY_DO + "," + UPDATED_TEN_LY_DO);

        // Get all the lyDoCongTacList where tenLyDo equals to UPDATED_TEN_LY_DO
        defaultLyDoCongTacShouldNotBeFound("tenLyDo.in=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByTenLyDoIsNullOrNotNull() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where tenLyDo is not null
        defaultLyDoCongTacShouldBeFound("tenLyDo.specified=true");

        // Get all the lyDoCongTacList where tenLyDo is null
        defaultLyDoCongTacShouldNotBeFound("tenLyDo.specified=false");
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByTenLyDoContainsSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where tenLyDo contains DEFAULT_TEN_LY_DO
        defaultLyDoCongTacShouldBeFound("tenLyDo.contains=" + DEFAULT_TEN_LY_DO);

        // Get all the lyDoCongTacList where tenLyDo contains UPDATED_TEN_LY_DO
        defaultLyDoCongTacShouldNotBeFound("tenLyDo.contains=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByTenLyDoNotContainsSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where tenLyDo does not contain DEFAULT_TEN_LY_DO
        defaultLyDoCongTacShouldNotBeFound("tenLyDo.doesNotContain=" + DEFAULT_TEN_LY_DO);

        // Get all the lyDoCongTacList where tenLyDo does not contain UPDATED_TEN_LY_DO
        defaultLyDoCongTacShouldBeFound("tenLyDo.doesNotContain=" + UPDATED_TEN_LY_DO);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByThuTuSXIsEqualToSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where thuTuSX equals to DEFAULT_THU_TU_SX
        defaultLyDoCongTacShouldBeFound("thuTuSX.equals=" + DEFAULT_THU_TU_SX);

        // Get all the lyDoCongTacList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultLyDoCongTacShouldNotBeFound("thuTuSX.equals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByThuTuSXIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where thuTuSX not equals to DEFAULT_THU_TU_SX
        defaultLyDoCongTacShouldNotBeFound("thuTuSX.notEquals=" + DEFAULT_THU_TU_SX);

        // Get all the lyDoCongTacList where thuTuSX not equals to UPDATED_THU_TU_SX
        defaultLyDoCongTacShouldBeFound("thuTuSX.notEquals=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByThuTuSXIsInShouldWork() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where thuTuSX in DEFAULT_THU_TU_SX or UPDATED_THU_TU_SX
        defaultLyDoCongTacShouldBeFound("thuTuSX.in=" + DEFAULT_THU_TU_SX + "," + UPDATED_THU_TU_SX);

        // Get all the lyDoCongTacList where thuTuSX equals to UPDATED_THU_TU_SX
        defaultLyDoCongTacShouldNotBeFound("thuTuSX.in=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByThuTuSXIsNullOrNotNull() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where thuTuSX is not null
        defaultLyDoCongTacShouldBeFound("thuTuSX.specified=true");

        // Get all the lyDoCongTacList where thuTuSX is null
        defaultLyDoCongTacShouldNotBeFound("thuTuSX.specified=false");
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByThuTuSXContainsSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where thuTuSX contains DEFAULT_THU_TU_SX
        defaultLyDoCongTacShouldBeFound("thuTuSX.contains=" + DEFAULT_THU_TU_SX);

        // Get all the lyDoCongTacList where thuTuSX contains UPDATED_THU_TU_SX
        defaultLyDoCongTacShouldNotBeFound("thuTuSX.contains=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByThuTuSXNotContainsSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        // Get all the lyDoCongTacList where thuTuSX does not contain DEFAULT_THU_TU_SX
        defaultLyDoCongTacShouldNotBeFound("thuTuSX.doesNotContain=" + DEFAULT_THU_TU_SX);

        // Get all the lyDoCongTacList where thuTuSX does not contain UPDATED_THU_TU_SX
        defaultLyDoCongTacShouldBeFound("thuTuSX.doesNotContain=" + UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void getAllLyDoCongTacsByChiDaoTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);
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
        lyDoCongTac.setChiDaoTuyen(chiDaoTuyen);
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);
        Long chiDaoTuyenId = chiDaoTuyen.getId();

        // Get all the lyDoCongTacList where chiDaoTuyen equals to chiDaoTuyenId
        defaultLyDoCongTacShouldBeFound("chiDaoTuyenId.equals=" + chiDaoTuyenId);

        // Get all the lyDoCongTacList where chiDaoTuyen equals to (chiDaoTuyenId + 1)
        defaultLyDoCongTacShouldNotBeFound("chiDaoTuyenId.equals=" + (chiDaoTuyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLyDoCongTacShouldBeFound(String filter) throws Exception {
        restLyDoCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lyDoCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLyDo").value(hasItem(DEFAULT_MA_LY_DO)))
            .andExpect(jsonPath("$.[*].tenLyDo").value(hasItem(DEFAULT_TEN_LY_DO)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));

        // Check, that the count call also returns 1
        restLyDoCongTacMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLyDoCongTacShouldNotBeFound(String filter) throws Exception {
        restLyDoCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLyDoCongTacMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingLyDoCongTac() throws Exception {
        // Get the lyDoCongTac
        restLyDoCongTacMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLyDoCongTac() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();

        // Update the lyDoCongTac
        LyDoCongTac updatedLyDoCongTac = lyDoCongTacRepository.findById(lyDoCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedLyDoCongTac are not directly saved in db
        em.detach(updatedLyDoCongTac);
        updatedLyDoCongTac.maLyDo(UPDATED_MA_LY_DO).tenLyDo(UPDATED_TEN_LY_DO).thuTuSX(UPDATED_THU_TU_SX);
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(updatedLyDoCongTac);

        restLyDoCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, lyDoCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isOk());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
        LyDoCongTac testLyDoCongTac = lyDoCongTacList.get(lyDoCongTacList.size() - 1);
        assertThat(testLyDoCongTac.getMaLyDo()).isEqualTo(UPDATED_MA_LY_DO);
        assertThat(testLyDoCongTac.getTenLyDo()).isEqualTo(UPDATED_TEN_LY_DO);
        assertThat(testLyDoCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingLyDoCongTac() throws Exception {
        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();
        lyDoCongTac.setId(count.incrementAndGet());

        // Create the LyDoCongTac
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLyDoCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, lyDoCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLyDoCongTac() throws Exception {
        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();
        lyDoCongTac.setId(count.incrementAndGet());

        // Create the LyDoCongTac
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLyDoCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLyDoCongTac() throws Exception {
        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();
        lyDoCongTac.setId(count.incrementAndGet());

        // Create the LyDoCongTac
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLyDoCongTacMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLyDoCongTacWithPatch() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();

        // Update the lyDoCongTac using partial update
        LyDoCongTac partialUpdatedLyDoCongTac = new LyDoCongTac();
        partialUpdatedLyDoCongTac.setId(lyDoCongTac.getId());

        partialUpdatedLyDoCongTac.thuTuSX(UPDATED_THU_TU_SX);

        restLyDoCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLyDoCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLyDoCongTac))
            )
            .andExpect(status().isOk());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
        LyDoCongTac testLyDoCongTac = lyDoCongTacList.get(lyDoCongTacList.size() - 1);
        assertThat(testLyDoCongTac.getMaLyDo()).isEqualTo(DEFAULT_MA_LY_DO);
        assertThat(testLyDoCongTac.getTenLyDo()).isEqualTo(DEFAULT_TEN_LY_DO);
        assertThat(testLyDoCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateLyDoCongTacWithPatch() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();

        // Update the lyDoCongTac using partial update
        LyDoCongTac partialUpdatedLyDoCongTac = new LyDoCongTac();
        partialUpdatedLyDoCongTac.setId(lyDoCongTac.getId());

        partialUpdatedLyDoCongTac.maLyDo(UPDATED_MA_LY_DO).tenLyDo(UPDATED_TEN_LY_DO).thuTuSX(UPDATED_THU_TU_SX);

        restLyDoCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLyDoCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLyDoCongTac))
            )
            .andExpect(status().isOk());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
        LyDoCongTac testLyDoCongTac = lyDoCongTacList.get(lyDoCongTacList.size() - 1);
        assertThat(testLyDoCongTac.getMaLyDo()).isEqualTo(UPDATED_MA_LY_DO);
        assertThat(testLyDoCongTac.getTenLyDo()).isEqualTo(UPDATED_TEN_LY_DO);
        assertThat(testLyDoCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingLyDoCongTac() throws Exception {
        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();
        lyDoCongTac.setId(count.incrementAndGet());

        // Create the LyDoCongTac
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLyDoCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, lyDoCongTacDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLyDoCongTac() throws Exception {
        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();
        lyDoCongTac.setId(count.incrementAndGet());

        // Create the LyDoCongTac
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLyDoCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLyDoCongTac() throws Exception {
        int databaseSizeBeforeUpdate = lyDoCongTacRepository.findAll().size();
        lyDoCongTac.setId(count.incrementAndGet());

        // Create the LyDoCongTac
        LyDoCongTacDTO lyDoCongTacDTO = lyDoCongTacMapper.toDto(lyDoCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLyDoCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lyDoCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LyDoCongTac in the database
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLyDoCongTac() throws Exception {
        // Initialize the database
        lyDoCongTacRepository.saveAndFlush(lyDoCongTac);

        int databaseSizeBeforeDelete = lyDoCongTacRepository.findAll().size();

        // Delete the lyDoCongTac
        restLyDoCongTacMockMvc
            .perform(delete(ENTITY_API_URL_ID, lyDoCongTac.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LyDoCongTac> lyDoCongTacList = lyDoCongTacRepository.findAll();
        assertThat(lyDoCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
