package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.repository.ChiDaoTuyenRepository;
import com.mycompany.myapp.service.criteria.ChiDaoTuyenCriteria;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.mapper.ChiDaoTuyenMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link ChiDaoTuyenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChiDaoTuyenResourceIT {

    private static final String DEFAULT_SO_QUYET_DINH = "AAAAAAAAAA";
    private static final String UPDATED_SO_QUYET_DINH = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_QUYET_DINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_QUYET_DINH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NGAY_QUYET_DINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_SO_HD = "AAAAAAAAAA";
    private static final String UPDATED_SO_HD = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_HD = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_HD = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NGAY_HD = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_LY_DO_CT = "AAAAAAAAAA";
    private static final String UPDATED_LY_DO_CT = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_CONG_TAC = "AAAAAAAAAA";
    private static final String UPDATED_NOI_CONG_TAC = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_BAT_DAU = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_BAT_DAU = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NGAY_BAT_DAU = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final ZonedDateTime DEFAULT_NGAY_KET_THUC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_KET_THUC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NGAY_KET_THUC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_TAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_TAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NGAY_TAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_NHAN_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_NHAN_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_KY_THUAT_HO_TRO = "AAAAAAAAAA";
    private static final String UPDATED_KY_THUAT_HO_TRO = "BBBBBBBBBB";

    private static final String DEFAULT_VAT_TU_HO_TRO = "AAAAAAAAAA";
    private static final String UPDATED_VAT_TU_HO_TRO = "BBBBBBBBBB";

    private static final String DEFAULT_SO_BN_KHAM_DIEU_TRI = "AAAAAAAAAA";
    private static final String UPDATED_SO_BN_KHAM_DIEU_TRI = "BBBBBBBBBB";

    private static final String DEFAULT_SO_BN_PHAU_THUAT = "AAAAAAAAAA";
    private static final String UPDATED_SO_BN_PHAU_THUAT = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CAN_BO_CHUYEN_GIAO = "AAAAAAAAAA";
    private static final String UPDATED_SO_CAN_BO_CHUYEN_GIAO = "BBBBBBBBBB";

    private static final String DEFAULT_KET_QUA_CONG_TAC = "AAAAAAAAAA";
    private static final String UPDATED_KET_QUA_CONG_TAC = "BBBBBBBBBB";

    private static final String DEFAULT_LUU_TRU = "AAAAAAAAAA";
    private static final String UPDATED_LUU_TRU = "BBBBBBBBBB";

    private static final String DEFAULT_TIEN_AN = "AAAAAAAAAA";
    private static final String UPDATED_TIEN_AN = "BBBBBBBBBB";

    private static final String DEFAULT_TIEN_O = "AAAAAAAAAA";
    private static final String UPDATED_TIEN_O = "BBBBBBBBBB";

    private static final String DEFAULT_TIEN_DI_LAI = "AAAAAAAAAA";
    private static final String UPDATED_TIEN_DI_LAI = "BBBBBBBBBB";

    private static final String DEFAULT_TAI_LIEU = "AAAAAAAAAA";
    private static final String UPDATED_TAI_LIEU = "BBBBBBBBBB";

    private static final String DEFAULT_GIANG_DAY = "AAAAAAAAAA";
    private static final String UPDATED_GIANG_DAY = "BBBBBBBBBB";

    private static final String DEFAULT_KHAC = "AAAAAAAAAA";
    private static final String UPDATED_KHAC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/chi-dao-tuyens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ChiDaoTuyenRepository chiDaoTuyenRepository;

    @Autowired
    private ChiDaoTuyenMapper chiDaoTuyenMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChiDaoTuyenMockMvc;

    private ChiDaoTuyen chiDaoTuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiDaoTuyen createEntity(EntityManager em) {
        ChiDaoTuyen chiDaoTuyen = new ChiDaoTuyen()
            .soQuyetDinh(DEFAULT_SO_QUYET_DINH)
            .ngayQuyetDinh(DEFAULT_NGAY_QUYET_DINH)
            .soHD(DEFAULT_SO_HD)
            .ngayHD(DEFAULT_NGAY_HD)
            .lyDoCT(DEFAULT_LY_DO_CT)
            .noiDung(DEFAULT_NOI_DUNG)
            .noiCongTac(DEFAULT_NOI_CONG_TAC)
            .ngayBatDau(DEFAULT_NGAY_BAT_DAU)
            .ngayKetThuc(DEFAULT_NGAY_KET_THUC)
            .ghiChu(DEFAULT_GHI_CHU)
            .ngayTao(DEFAULT_NGAY_TAO)
            .nhanVien(DEFAULT_NHAN_VIEN)
            .kyThuatHoTro(DEFAULT_KY_THUAT_HO_TRO)
            .vatTuHoTro(DEFAULT_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(DEFAULT_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(DEFAULT_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(DEFAULT_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(DEFAULT_KET_QUA_CONG_TAC)
            .luuTru(DEFAULT_LUU_TRU)
            .tienAn(DEFAULT_TIEN_AN)
            .tienO(DEFAULT_TIEN_O)
            .tienDiLai(DEFAULT_TIEN_DI_LAI)
            .taiLieu(DEFAULT_TAI_LIEU)
            .giangDay(DEFAULT_GIANG_DAY)
            .khac(DEFAULT_KHAC);
        return chiDaoTuyen;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiDaoTuyen createUpdatedEntity(EntityManager em) {
        ChiDaoTuyen chiDaoTuyen = new ChiDaoTuyen()
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .lyDoCT(UPDATED_LY_DO_CT)
            .noiDung(UPDATED_NOI_DUNG)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ngayBatDau(UPDATED_NGAY_BAT_DAU)
            .ngayKetThuc(UPDATED_NGAY_KET_THUC)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayTao(UPDATED_NGAY_TAO)
            .nhanVien(UPDATED_NHAN_VIEN)
            .kyThuatHoTro(UPDATED_KY_THUAT_HO_TRO)
            .vatTuHoTro(UPDATED_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(UPDATED_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(UPDATED_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(UPDATED_KET_QUA_CONG_TAC)
            .luuTru(UPDATED_LUU_TRU)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .tienDiLai(UPDATED_TIEN_DI_LAI)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY)
            .khac(UPDATED_KHAC);
        return chiDaoTuyen;
    }

    @BeforeEach
    public void initTest() {
        chiDaoTuyen = createEntity(em);
    }

    @Test
    @Transactional
    void createChiDaoTuyen() throws Exception {
        int databaseSizeBeforeCreate = chiDaoTuyenRepository.findAll().size();
        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);
        restChiDaoTuyenMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeCreate + 1);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(DEFAULT_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(DEFAULT_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(DEFAULT_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(DEFAULT_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(DEFAULT_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(DEFAULT_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(DEFAULT_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(DEFAULT_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testChiDaoTuyen.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testChiDaoTuyen.getNhanVien()).isEqualTo(DEFAULT_NHAN_VIEN);
        assertThat(testChiDaoTuyen.getKyThuatHoTro()).isEqualTo(DEFAULT_KY_THUAT_HO_TRO);
        assertThat(testChiDaoTuyen.getVatTuHoTro()).isEqualTo(DEFAULT_VAT_TU_HO_TRO);
        assertThat(testChiDaoTuyen.getSoBnKhamDieuTri()).isEqualTo(DEFAULT_SO_BN_KHAM_DIEU_TRI);
        assertThat(testChiDaoTuyen.getSoBnPhauThuat()).isEqualTo(DEFAULT_SO_BN_PHAU_THUAT);
        assertThat(testChiDaoTuyen.getSoCanBoChuyenGiao()).isEqualTo(DEFAULT_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testChiDaoTuyen.getKetQuaCongTac()).isEqualTo(DEFAULT_KET_QUA_CONG_TAC);
        assertThat(testChiDaoTuyen.getLuuTru()).isEqualTo(DEFAULT_LUU_TRU);
        assertThat(testChiDaoTuyen.getTienAn()).isEqualTo(DEFAULT_TIEN_AN);
        assertThat(testChiDaoTuyen.getTienO()).isEqualTo(DEFAULT_TIEN_O);
        assertThat(testChiDaoTuyen.getTienDiLai()).isEqualTo(DEFAULT_TIEN_DI_LAI);
        assertThat(testChiDaoTuyen.getTaiLieu()).isEqualTo(DEFAULT_TAI_LIEU);
        assertThat(testChiDaoTuyen.getGiangDay()).isEqualTo(DEFAULT_GIANG_DAY);
        assertThat(testChiDaoTuyen.getKhac()).isEqualTo(DEFAULT_KHAC);
    }

    @Test
    @Transactional
    void createChiDaoTuyenWithExistingId() throws Exception {
        // Create the ChiDaoTuyen with an existing ID
        chiDaoTuyen.setId(1L);
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        int databaseSizeBeforeCreate = chiDaoTuyenRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiDaoTuyenMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyens() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiDaoTuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].soQuyetDinh").value(hasItem(DEFAULT_SO_QUYET_DINH)))
            .andExpect(jsonPath("$.[*].ngayQuyetDinh").value(hasItem(sameInstant(DEFAULT_NGAY_QUYET_DINH))))
            .andExpect(jsonPath("$.[*].soHD").value(hasItem(DEFAULT_SO_HD)))
            .andExpect(jsonPath("$.[*].ngayHD").value(hasItem(sameInstant(DEFAULT_NGAY_HD))))
            .andExpect(jsonPath("$.[*].lyDoCT").value(hasItem(DEFAULT_LY_DO_CT)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].noiCongTac").value(hasItem(DEFAULT_NOI_CONG_TAC)))
            .andExpect(jsonPath("$.[*].ngayBatDau").value(hasItem(sameInstant(DEFAULT_NGAY_BAT_DAU))))
            .andExpect(jsonPath("$.[*].ngayKetThuc").value(hasItem(sameInstant(DEFAULT_NGAY_KET_THUC))))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(sameInstant(DEFAULT_NGAY_TAO))))
            .andExpect(jsonPath("$.[*].nhanVien").value(hasItem(DEFAULT_NHAN_VIEN)))
            .andExpect(jsonPath("$.[*].kyThuatHoTro").value(hasItem(DEFAULT_KY_THUAT_HO_TRO)))
            .andExpect(jsonPath("$.[*].vatTuHoTro").value(hasItem(DEFAULT_VAT_TU_HO_TRO)))
            .andExpect(jsonPath("$.[*].soBnKhamDieuTri").value(hasItem(DEFAULT_SO_BN_KHAM_DIEU_TRI)))
            .andExpect(jsonPath("$.[*].soBnPhauThuat").value(hasItem(DEFAULT_SO_BN_PHAU_THUAT)))
            .andExpect(jsonPath("$.[*].soCanBoChuyenGiao").value(hasItem(DEFAULT_SO_CAN_BO_CHUYEN_GIAO)))
            .andExpect(jsonPath("$.[*].ketQuaCongTac").value(hasItem(DEFAULT_KET_QUA_CONG_TAC)))
            .andExpect(jsonPath("$.[*].luuTru").value(hasItem(DEFAULT_LUU_TRU)))
            .andExpect(jsonPath("$.[*].tienAn").value(hasItem(DEFAULT_TIEN_AN)))
            .andExpect(jsonPath("$.[*].tienO").value(hasItem(DEFAULT_TIEN_O)))
            .andExpect(jsonPath("$.[*].tienDiLai").value(hasItem(DEFAULT_TIEN_DI_LAI)))
            .andExpect(jsonPath("$.[*].taiLieu").value(hasItem(DEFAULT_TAI_LIEU)))
            .andExpect(jsonPath("$.[*].giangDay").value(hasItem(DEFAULT_GIANG_DAY)))
            .andExpect(jsonPath("$.[*].khac").value(hasItem(DEFAULT_KHAC)));
    }

    @Test
    @Transactional
    void getChiDaoTuyen() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get the chiDaoTuyen
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL_ID, chiDaoTuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chiDaoTuyen.getId().intValue()))
            .andExpect(jsonPath("$.soQuyetDinh").value(DEFAULT_SO_QUYET_DINH))
            .andExpect(jsonPath("$.ngayQuyetDinh").value(sameInstant(DEFAULT_NGAY_QUYET_DINH)))
            .andExpect(jsonPath("$.soHD").value(DEFAULT_SO_HD))
            .andExpect(jsonPath("$.ngayHD").value(sameInstant(DEFAULT_NGAY_HD)))
            .andExpect(jsonPath("$.lyDoCT").value(DEFAULT_LY_DO_CT))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG))
            .andExpect(jsonPath("$.noiCongTac").value(DEFAULT_NOI_CONG_TAC))
            .andExpect(jsonPath("$.ngayBatDau").value(sameInstant(DEFAULT_NGAY_BAT_DAU)))
            .andExpect(jsonPath("$.ngayKetThuc").value(sameInstant(DEFAULT_NGAY_KET_THUC)))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.ngayTao").value(sameInstant(DEFAULT_NGAY_TAO)))
            .andExpect(jsonPath("$.nhanVien").value(DEFAULT_NHAN_VIEN))
            .andExpect(jsonPath("$.kyThuatHoTro").value(DEFAULT_KY_THUAT_HO_TRO))
            .andExpect(jsonPath("$.vatTuHoTro").value(DEFAULT_VAT_TU_HO_TRO))
            .andExpect(jsonPath("$.soBnKhamDieuTri").value(DEFAULT_SO_BN_KHAM_DIEU_TRI))
            .andExpect(jsonPath("$.soBnPhauThuat").value(DEFAULT_SO_BN_PHAU_THUAT))
            .andExpect(jsonPath("$.soCanBoChuyenGiao").value(DEFAULT_SO_CAN_BO_CHUYEN_GIAO))
            .andExpect(jsonPath("$.ketQuaCongTac").value(DEFAULT_KET_QUA_CONG_TAC))
            .andExpect(jsonPath("$.luuTru").value(DEFAULT_LUU_TRU))
            .andExpect(jsonPath("$.tienAn").value(DEFAULT_TIEN_AN))
            .andExpect(jsonPath("$.tienO").value(DEFAULT_TIEN_O))
            .andExpect(jsonPath("$.tienDiLai").value(DEFAULT_TIEN_DI_LAI))
            .andExpect(jsonPath("$.taiLieu").value(DEFAULT_TAI_LIEU))
            .andExpect(jsonPath("$.giangDay").value(DEFAULT_GIANG_DAY))
            .andExpect(jsonPath("$.khac").value(DEFAULT_KHAC));
    }

    @Test
    @Transactional
    void getChiDaoTuyensByIdFiltering() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        Long id = chiDaoTuyen.getId();

        defaultChiDaoTuyenShouldBeFound("id.equals=" + id);
        defaultChiDaoTuyenShouldNotBeFound("id.notEquals=" + id);

        defaultChiDaoTuyenShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultChiDaoTuyenShouldNotBeFound("id.greaterThan=" + id);

        defaultChiDaoTuyenShouldBeFound("id.lessThanOrEqual=" + id);
        defaultChiDaoTuyenShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoQuyetDinhIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soQuyetDinh equals to DEFAULT_SO_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("soQuyetDinh.equals=" + DEFAULT_SO_QUYET_DINH);

        // Get all the chiDaoTuyenList where soQuyetDinh equals to UPDATED_SO_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("soQuyetDinh.equals=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoQuyetDinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soQuyetDinh not equals to DEFAULT_SO_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("soQuyetDinh.notEquals=" + DEFAULT_SO_QUYET_DINH);

        // Get all the chiDaoTuyenList where soQuyetDinh not equals to UPDATED_SO_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("soQuyetDinh.notEquals=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoQuyetDinhIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soQuyetDinh in DEFAULT_SO_QUYET_DINH or UPDATED_SO_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("soQuyetDinh.in=" + DEFAULT_SO_QUYET_DINH + "," + UPDATED_SO_QUYET_DINH);

        // Get all the chiDaoTuyenList where soQuyetDinh equals to UPDATED_SO_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("soQuyetDinh.in=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoQuyetDinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soQuyetDinh is not null
        defaultChiDaoTuyenShouldBeFound("soQuyetDinh.specified=true");

        // Get all the chiDaoTuyenList where soQuyetDinh is null
        defaultChiDaoTuyenShouldNotBeFound("soQuyetDinh.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoQuyetDinhContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soQuyetDinh contains DEFAULT_SO_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("soQuyetDinh.contains=" + DEFAULT_SO_QUYET_DINH);

        // Get all the chiDaoTuyenList where soQuyetDinh contains UPDATED_SO_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("soQuyetDinh.contains=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoQuyetDinhNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soQuyetDinh does not contain DEFAULT_SO_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("soQuyetDinh.doesNotContain=" + DEFAULT_SO_QUYET_DINH);

        // Get all the chiDaoTuyenList where soQuyetDinh does not contain UPDATED_SO_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("soQuyetDinh.doesNotContain=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh equals to DEFAULT_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.equals=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the chiDaoTuyenList where ngayQuyetDinh equals to UPDATED_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.equals=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh not equals to DEFAULT_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.notEquals=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the chiDaoTuyenList where ngayQuyetDinh not equals to UPDATED_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.notEquals=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh in DEFAULT_NGAY_QUYET_DINH or UPDATED_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.in=" + DEFAULT_NGAY_QUYET_DINH + "," + UPDATED_NGAY_QUYET_DINH);

        // Get all the chiDaoTuyenList where ngayQuyetDinh equals to UPDATED_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.in=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is not null
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.specified=true");

        // Get all the chiDaoTuyenList where ngayQuyetDinh is null
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is greater than or equal to DEFAULT_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.greaterThanOrEqual=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is greater than or equal to UPDATED_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.greaterThanOrEqual=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is less than or equal to DEFAULT_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.lessThanOrEqual=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is less than or equal to SMALLER_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.lessThanOrEqual=" + SMALLER_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsLessThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is less than DEFAULT_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.lessThan=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is less than UPDATED_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.lessThan=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayQuyetDinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is greater than DEFAULT_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.greaterThan=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the chiDaoTuyenList where ngayQuyetDinh is greater than SMALLER_NGAY_QUYET_DINH
        defaultChiDaoTuyenShouldBeFound("ngayQuyetDinh.greaterThan=" + SMALLER_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoHDIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soHD equals to DEFAULT_SO_HD
        defaultChiDaoTuyenShouldBeFound("soHD.equals=" + DEFAULT_SO_HD);

        // Get all the chiDaoTuyenList where soHD equals to UPDATED_SO_HD
        defaultChiDaoTuyenShouldNotBeFound("soHD.equals=" + UPDATED_SO_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoHDIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soHD not equals to DEFAULT_SO_HD
        defaultChiDaoTuyenShouldNotBeFound("soHD.notEquals=" + DEFAULT_SO_HD);

        // Get all the chiDaoTuyenList where soHD not equals to UPDATED_SO_HD
        defaultChiDaoTuyenShouldBeFound("soHD.notEquals=" + UPDATED_SO_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoHDIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soHD in DEFAULT_SO_HD or UPDATED_SO_HD
        defaultChiDaoTuyenShouldBeFound("soHD.in=" + DEFAULT_SO_HD + "," + UPDATED_SO_HD);

        // Get all the chiDaoTuyenList where soHD equals to UPDATED_SO_HD
        defaultChiDaoTuyenShouldNotBeFound("soHD.in=" + UPDATED_SO_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoHDIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soHD is not null
        defaultChiDaoTuyenShouldBeFound("soHD.specified=true");

        // Get all the chiDaoTuyenList where soHD is null
        defaultChiDaoTuyenShouldNotBeFound("soHD.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoHDContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soHD contains DEFAULT_SO_HD
        defaultChiDaoTuyenShouldBeFound("soHD.contains=" + DEFAULT_SO_HD);

        // Get all the chiDaoTuyenList where soHD contains UPDATED_SO_HD
        defaultChiDaoTuyenShouldNotBeFound("soHD.contains=" + UPDATED_SO_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoHDNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soHD does not contain DEFAULT_SO_HD
        defaultChiDaoTuyenShouldNotBeFound("soHD.doesNotContain=" + DEFAULT_SO_HD);

        // Get all the chiDaoTuyenList where soHD does not contain UPDATED_SO_HD
        defaultChiDaoTuyenShouldBeFound("soHD.doesNotContain=" + UPDATED_SO_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD equals to DEFAULT_NGAY_HD
        defaultChiDaoTuyenShouldBeFound("ngayHD.equals=" + DEFAULT_NGAY_HD);

        // Get all the chiDaoTuyenList where ngayHD equals to UPDATED_NGAY_HD
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.equals=" + UPDATED_NGAY_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD not equals to DEFAULT_NGAY_HD
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.notEquals=" + DEFAULT_NGAY_HD);

        // Get all the chiDaoTuyenList where ngayHD not equals to UPDATED_NGAY_HD
        defaultChiDaoTuyenShouldBeFound("ngayHD.notEquals=" + UPDATED_NGAY_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD in DEFAULT_NGAY_HD or UPDATED_NGAY_HD
        defaultChiDaoTuyenShouldBeFound("ngayHD.in=" + DEFAULT_NGAY_HD + "," + UPDATED_NGAY_HD);

        // Get all the chiDaoTuyenList where ngayHD equals to UPDATED_NGAY_HD
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.in=" + UPDATED_NGAY_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD is not null
        defaultChiDaoTuyenShouldBeFound("ngayHD.specified=true");

        // Get all the chiDaoTuyenList where ngayHD is null
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD is greater than or equal to DEFAULT_NGAY_HD
        defaultChiDaoTuyenShouldBeFound("ngayHD.greaterThanOrEqual=" + DEFAULT_NGAY_HD);

        // Get all the chiDaoTuyenList where ngayHD is greater than or equal to UPDATED_NGAY_HD
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.greaterThanOrEqual=" + UPDATED_NGAY_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD is less than or equal to DEFAULT_NGAY_HD
        defaultChiDaoTuyenShouldBeFound("ngayHD.lessThanOrEqual=" + DEFAULT_NGAY_HD);

        // Get all the chiDaoTuyenList where ngayHD is less than or equal to SMALLER_NGAY_HD
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.lessThanOrEqual=" + SMALLER_NGAY_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsLessThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD is less than DEFAULT_NGAY_HD
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.lessThan=" + DEFAULT_NGAY_HD);

        // Get all the chiDaoTuyenList where ngayHD is less than UPDATED_NGAY_HD
        defaultChiDaoTuyenShouldBeFound("ngayHD.lessThan=" + UPDATED_NGAY_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayHDIsGreaterThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayHD is greater than DEFAULT_NGAY_HD
        defaultChiDaoTuyenShouldNotBeFound("ngayHD.greaterThan=" + DEFAULT_NGAY_HD);

        // Get all the chiDaoTuyenList where ngayHD is greater than SMALLER_NGAY_HD
        defaultChiDaoTuyenShouldBeFound("ngayHD.greaterThan=" + SMALLER_NGAY_HD);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLyDoCTIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where lyDoCT equals to DEFAULT_LY_DO_CT
        defaultChiDaoTuyenShouldBeFound("lyDoCT.equals=" + DEFAULT_LY_DO_CT);

        // Get all the chiDaoTuyenList where lyDoCT equals to UPDATED_LY_DO_CT
        defaultChiDaoTuyenShouldNotBeFound("lyDoCT.equals=" + UPDATED_LY_DO_CT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLyDoCTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where lyDoCT not equals to DEFAULT_LY_DO_CT
        defaultChiDaoTuyenShouldNotBeFound("lyDoCT.notEquals=" + DEFAULT_LY_DO_CT);

        // Get all the chiDaoTuyenList where lyDoCT not equals to UPDATED_LY_DO_CT
        defaultChiDaoTuyenShouldBeFound("lyDoCT.notEquals=" + UPDATED_LY_DO_CT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLyDoCTIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where lyDoCT in DEFAULT_LY_DO_CT or UPDATED_LY_DO_CT
        defaultChiDaoTuyenShouldBeFound("lyDoCT.in=" + DEFAULT_LY_DO_CT + "," + UPDATED_LY_DO_CT);

        // Get all the chiDaoTuyenList where lyDoCT equals to UPDATED_LY_DO_CT
        defaultChiDaoTuyenShouldNotBeFound("lyDoCT.in=" + UPDATED_LY_DO_CT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLyDoCTIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where lyDoCT is not null
        defaultChiDaoTuyenShouldBeFound("lyDoCT.specified=true");

        // Get all the chiDaoTuyenList where lyDoCT is null
        defaultChiDaoTuyenShouldNotBeFound("lyDoCT.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLyDoCTContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where lyDoCT contains DEFAULT_LY_DO_CT
        defaultChiDaoTuyenShouldBeFound("lyDoCT.contains=" + DEFAULT_LY_DO_CT);

        // Get all the chiDaoTuyenList where lyDoCT contains UPDATED_LY_DO_CT
        defaultChiDaoTuyenShouldNotBeFound("lyDoCT.contains=" + UPDATED_LY_DO_CT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLyDoCTNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where lyDoCT does not contain DEFAULT_LY_DO_CT
        defaultChiDaoTuyenShouldNotBeFound("lyDoCT.doesNotContain=" + DEFAULT_LY_DO_CT);

        // Get all the chiDaoTuyenList where lyDoCT does not contain UPDATED_LY_DO_CT
        defaultChiDaoTuyenShouldBeFound("lyDoCT.doesNotContain=" + UPDATED_LY_DO_CT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiDungIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiDung equals to DEFAULT_NOI_DUNG
        defaultChiDaoTuyenShouldBeFound("noiDung.equals=" + DEFAULT_NOI_DUNG);

        // Get all the chiDaoTuyenList where noiDung equals to UPDATED_NOI_DUNG
        defaultChiDaoTuyenShouldNotBeFound("noiDung.equals=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiDungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiDung not equals to DEFAULT_NOI_DUNG
        defaultChiDaoTuyenShouldNotBeFound("noiDung.notEquals=" + DEFAULT_NOI_DUNG);

        // Get all the chiDaoTuyenList where noiDung not equals to UPDATED_NOI_DUNG
        defaultChiDaoTuyenShouldBeFound("noiDung.notEquals=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiDungIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiDung in DEFAULT_NOI_DUNG or UPDATED_NOI_DUNG
        defaultChiDaoTuyenShouldBeFound("noiDung.in=" + DEFAULT_NOI_DUNG + "," + UPDATED_NOI_DUNG);

        // Get all the chiDaoTuyenList where noiDung equals to UPDATED_NOI_DUNG
        defaultChiDaoTuyenShouldNotBeFound("noiDung.in=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiDungIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiDung is not null
        defaultChiDaoTuyenShouldBeFound("noiDung.specified=true");

        // Get all the chiDaoTuyenList where noiDung is null
        defaultChiDaoTuyenShouldNotBeFound("noiDung.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiDungContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiDung contains DEFAULT_NOI_DUNG
        defaultChiDaoTuyenShouldBeFound("noiDung.contains=" + DEFAULT_NOI_DUNG);

        // Get all the chiDaoTuyenList where noiDung contains UPDATED_NOI_DUNG
        defaultChiDaoTuyenShouldNotBeFound("noiDung.contains=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiDungNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiDung does not contain DEFAULT_NOI_DUNG
        defaultChiDaoTuyenShouldNotBeFound("noiDung.doesNotContain=" + DEFAULT_NOI_DUNG);

        // Get all the chiDaoTuyenList where noiDung does not contain UPDATED_NOI_DUNG
        defaultChiDaoTuyenShouldBeFound("noiDung.doesNotContain=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiCongTacIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiCongTac equals to DEFAULT_NOI_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("noiCongTac.equals=" + DEFAULT_NOI_CONG_TAC);

        // Get all the chiDaoTuyenList where noiCongTac equals to UPDATED_NOI_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("noiCongTac.equals=" + UPDATED_NOI_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiCongTacIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiCongTac not equals to DEFAULT_NOI_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("noiCongTac.notEquals=" + DEFAULT_NOI_CONG_TAC);

        // Get all the chiDaoTuyenList where noiCongTac not equals to UPDATED_NOI_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("noiCongTac.notEquals=" + UPDATED_NOI_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiCongTacIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiCongTac in DEFAULT_NOI_CONG_TAC or UPDATED_NOI_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("noiCongTac.in=" + DEFAULT_NOI_CONG_TAC + "," + UPDATED_NOI_CONG_TAC);

        // Get all the chiDaoTuyenList where noiCongTac equals to UPDATED_NOI_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("noiCongTac.in=" + UPDATED_NOI_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiCongTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiCongTac is not null
        defaultChiDaoTuyenShouldBeFound("noiCongTac.specified=true");

        // Get all the chiDaoTuyenList where noiCongTac is null
        defaultChiDaoTuyenShouldNotBeFound("noiCongTac.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiCongTacContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiCongTac contains DEFAULT_NOI_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("noiCongTac.contains=" + DEFAULT_NOI_CONG_TAC);

        // Get all the chiDaoTuyenList where noiCongTac contains UPDATED_NOI_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("noiCongTac.contains=" + UPDATED_NOI_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNoiCongTacNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where noiCongTac does not contain DEFAULT_NOI_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("noiCongTac.doesNotContain=" + DEFAULT_NOI_CONG_TAC);

        // Get all the chiDaoTuyenList where noiCongTac does not contain UPDATED_NOI_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("noiCongTac.doesNotContain=" + UPDATED_NOI_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau equals to DEFAULT_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.equals=" + DEFAULT_NGAY_BAT_DAU);

        // Get all the chiDaoTuyenList where ngayBatDau equals to UPDATED_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.equals=" + UPDATED_NGAY_BAT_DAU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau not equals to DEFAULT_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.notEquals=" + DEFAULT_NGAY_BAT_DAU);

        // Get all the chiDaoTuyenList where ngayBatDau not equals to UPDATED_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.notEquals=" + UPDATED_NGAY_BAT_DAU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau in DEFAULT_NGAY_BAT_DAU or UPDATED_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.in=" + DEFAULT_NGAY_BAT_DAU + "," + UPDATED_NGAY_BAT_DAU);

        // Get all the chiDaoTuyenList where ngayBatDau equals to UPDATED_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.in=" + UPDATED_NGAY_BAT_DAU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau is not null
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.specified=true");

        // Get all the chiDaoTuyenList where ngayBatDau is null
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau is greater than or equal to DEFAULT_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.greaterThanOrEqual=" + DEFAULT_NGAY_BAT_DAU);

        // Get all the chiDaoTuyenList where ngayBatDau is greater than or equal to UPDATED_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.greaterThanOrEqual=" + UPDATED_NGAY_BAT_DAU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau is less than or equal to DEFAULT_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.lessThanOrEqual=" + DEFAULT_NGAY_BAT_DAU);

        // Get all the chiDaoTuyenList where ngayBatDau is less than or equal to SMALLER_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.lessThanOrEqual=" + SMALLER_NGAY_BAT_DAU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsLessThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau is less than DEFAULT_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.lessThan=" + DEFAULT_NGAY_BAT_DAU);

        // Get all the chiDaoTuyenList where ngayBatDau is less than UPDATED_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.lessThan=" + UPDATED_NGAY_BAT_DAU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayBatDauIsGreaterThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayBatDau is greater than DEFAULT_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldNotBeFound("ngayBatDau.greaterThan=" + DEFAULT_NGAY_BAT_DAU);

        // Get all the chiDaoTuyenList where ngayBatDau is greater than SMALLER_NGAY_BAT_DAU
        defaultChiDaoTuyenShouldBeFound("ngayBatDau.greaterThan=" + SMALLER_NGAY_BAT_DAU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc equals to DEFAULT_NGAY_KET_THUC
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.equals=" + DEFAULT_NGAY_KET_THUC);

        // Get all the chiDaoTuyenList where ngayKetThuc equals to UPDATED_NGAY_KET_THUC
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.equals=" + UPDATED_NGAY_KET_THUC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc not equals to DEFAULT_NGAY_KET_THUC
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.notEquals=" + DEFAULT_NGAY_KET_THUC);

        // Get all the chiDaoTuyenList where ngayKetThuc not equals to UPDATED_NGAY_KET_THUC
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.notEquals=" + UPDATED_NGAY_KET_THUC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc in DEFAULT_NGAY_KET_THUC or UPDATED_NGAY_KET_THUC
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.in=" + DEFAULT_NGAY_KET_THUC + "," + UPDATED_NGAY_KET_THUC);

        // Get all the chiDaoTuyenList where ngayKetThuc equals to UPDATED_NGAY_KET_THUC
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.in=" + UPDATED_NGAY_KET_THUC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc is not null
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.specified=true");

        // Get all the chiDaoTuyenList where ngayKetThuc is null
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc is greater than or equal to DEFAULT_NGAY_KET_THUC
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.greaterThanOrEqual=" + DEFAULT_NGAY_KET_THUC);

        // Get all the chiDaoTuyenList where ngayKetThuc is greater than or equal to UPDATED_NGAY_KET_THUC
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.greaterThanOrEqual=" + UPDATED_NGAY_KET_THUC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc is less than or equal to DEFAULT_NGAY_KET_THUC
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.lessThanOrEqual=" + DEFAULT_NGAY_KET_THUC);

        // Get all the chiDaoTuyenList where ngayKetThuc is less than or equal to SMALLER_NGAY_KET_THUC
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.lessThanOrEqual=" + SMALLER_NGAY_KET_THUC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsLessThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc is less than DEFAULT_NGAY_KET_THUC
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.lessThan=" + DEFAULT_NGAY_KET_THUC);

        // Get all the chiDaoTuyenList where ngayKetThuc is less than UPDATED_NGAY_KET_THUC
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.lessThan=" + UPDATED_NGAY_KET_THUC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayKetThucIsGreaterThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayKetThuc is greater than DEFAULT_NGAY_KET_THUC
        defaultChiDaoTuyenShouldNotBeFound("ngayKetThuc.greaterThan=" + DEFAULT_NGAY_KET_THUC);

        // Get all the chiDaoTuyenList where ngayKetThuc is greater than SMALLER_NGAY_KET_THUC
        defaultChiDaoTuyenShouldBeFound("ngayKetThuc.greaterThan=" + SMALLER_NGAY_KET_THUC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ghiChu equals to DEFAULT_GHI_CHU
        defaultChiDaoTuyenShouldBeFound("ghiChu.equals=" + DEFAULT_GHI_CHU);

        // Get all the chiDaoTuyenList where ghiChu equals to UPDATED_GHI_CHU
        defaultChiDaoTuyenShouldNotBeFound("ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGhiChuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ghiChu not equals to DEFAULT_GHI_CHU
        defaultChiDaoTuyenShouldNotBeFound("ghiChu.notEquals=" + DEFAULT_GHI_CHU);

        // Get all the chiDaoTuyenList where ghiChu not equals to UPDATED_GHI_CHU
        defaultChiDaoTuyenShouldBeFound("ghiChu.notEquals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ghiChu in DEFAULT_GHI_CHU or UPDATED_GHI_CHU
        defaultChiDaoTuyenShouldBeFound("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU);

        // Get all the chiDaoTuyenList where ghiChu equals to UPDATED_GHI_CHU
        defaultChiDaoTuyenShouldNotBeFound("ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ghiChu is not null
        defaultChiDaoTuyenShouldBeFound("ghiChu.specified=true");

        // Get all the chiDaoTuyenList where ghiChu is null
        defaultChiDaoTuyenShouldNotBeFound("ghiChu.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ghiChu contains DEFAULT_GHI_CHU
        defaultChiDaoTuyenShouldBeFound("ghiChu.contains=" + DEFAULT_GHI_CHU);

        // Get all the chiDaoTuyenList where ghiChu contains UPDATED_GHI_CHU
        defaultChiDaoTuyenShouldNotBeFound("ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ghiChu does not contain DEFAULT_GHI_CHU
        defaultChiDaoTuyenShouldNotBeFound("ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);

        // Get all the chiDaoTuyenList where ghiChu does not contain UPDATED_GHI_CHU
        defaultChiDaoTuyenShouldBeFound("ghiChu.doesNotContain=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao equals to DEFAULT_NGAY_TAO
        defaultChiDaoTuyenShouldBeFound("ngayTao.equals=" + DEFAULT_NGAY_TAO);

        // Get all the chiDaoTuyenList where ngayTao equals to UPDATED_NGAY_TAO
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.equals=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao not equals to DEFAULT_NGAY_TAO
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.notEquals=" + DEFAULT_NGAY_TAO);

        // Get all the chiDaoTuyenList where ngayTao not equals to UPDATED_NGAY_TAO
        defaultChiDaoTuyenShouldBeFound("ngayTao.notEquals=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao in DEFAULT_NGAY_TAO or UPDATED_NGAY_TAO
        defaultChiDaoTuyenShouldBeFound("ngayTao.in=" + DEFAULT_NGAY_TAO + "," + UPDATED_NGAY_TAO);

        // Get all the chiDaoTuyenList where ngayTao equals to UPDATED_NGAY_TAO
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.in=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao is not null
        defaultChiDaoTuyenShouldBeFound("ngayTao.specified=true");

        // Get all the chiDaoTuyenList where ngayTao is null
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao is greater than or equal to DEFAULT_NGAY_TAO
        defaultChiDaoTuyenShouldBeFound("ngayTao.greaterThanOrEqual=" + DEFAULT_NGAY_TAO);

        // Get all the chiDaoTuyenList where ngayTao is greater than or equal to UPDATED_NGAY_TAO
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.greaterThanOrEqual=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao is less than or equal to DEFAULT_NGAY_TAO
        defaultChiDaoTuyenShouldBeFound("ngayTao.lessThanOrEqual=" + DEFAULT_NGAY_TAO);

        // Get all the chiDaoTuyenList where ngayTao is less than or equal to SMALLER_NGAY_TAO
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.lessThanOrEqual=" + SMALLER_NGAY_TAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsLessThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao is less than DEFAULT_NGAY_TAO
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.lessThan=" + DEFAULT_NGAY_TAO);

        // Get all the chiDaoTuyenList where ngayTao is less than UPDATED_NGAY_TAO
        defaultChiDaoTuyenShouldBeFound("ngayTao.lessThan=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNgayTaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ngayTao is greater than DEFAULT_NGAY_TAO
        defaultChiDaoTuyenShouldNotBeFound("ngayTao.greaterThan=" + DEFAULT_NGAY_TAO);

        // Get all the chiDaoTuyenList where ngayTao is greater than SMALLER_NGAY_TAO
        defaultChiDaoTuyenShouldBeFound("ngayTao.greaterThan=" + SMALLER_NGAY_TAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNhanVienIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where nhanVien equals to DEFAULT_NHAN_VIEN
        defaultChiDaoTuyenShouldBeFound("nhanVien.equals=" + DEFAULT_NHAN_VIEN);

        // Get all the chiDaoTuyenList where nhanVien equals to UPDATED_NHAN_VIEN
        defaultChiDaoTuyenShouldNotBeFound("nhanVien.equals=" + UPDATED_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNhanVienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where nhanVien not equals to DEFAULT_NHAN_VIEN
        defaultChiDaoTuyenShouldNotBeFound("nhanVien.notEquals=" + DEFAULT_NHAN_VIEN);

        // Get all the chiDaoTuyenList where nhanVien not equals to UPDATED_NHAN_VIEN
        defaultChiDaoTuyenShouldBeFound("nhanVien.notEquals=" + UPDATED_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNhanVienIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where nhanVien in DEFAULT_NHAN_VIEN or UPDATED_NHAN_VIEN
        defaultChiDaoTuyenShouldBeFound("nhanVien.in=" + DEFAULT_NHAN_VIEN + "," + UPDATED_NHAN_VIEN);

        // Get all the chiDaoTuyenList where nhanVien equals to UPDATED_NHAN_VIEN
        defaultChiDaoTuyenShouldNotBeFound("nhanVien.in=" + UPDATED_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNhanVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where nhanVien is not null
        defaultChiDaoTuyenShouldBeFound("nhanVien.specified=true");

        // Get all the chiDaoTuyenList where nhanVien is null
        defaultChiDaoTuyenShouldNotBeFound("nhanVien.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNhanVienContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where nhanVien contains DEFAULT_NHAN_VIEN
        defaultChiDaoTuyenShouldBeFound("nhanVien.contains=" + DEFAULT_NHAN_VIEN);

        // Get all the chiDaoTuyenList where nhanVien contains UPDATED_NHAN_VIEN
        defaultChiDaoTuyenShouldNotBeFound("nhanVien.contains=" + UPDATED_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByNhanVienNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where nhanVien does not contain DEFAULT_NHAN_VIEN
        defaultChiDaoTuyenShouldNotBeFound("nhanVien.doesNotContain=" + DEFAULT_NHAN_VIEN);

        // Get all the chiDaoTuyenList where nhanVien does not contain UPDATED_NHAN_VIEN
        defaultChiDaoTuyenShouldBeFound("nhanVien.doesNotContain=" + UPDATED_NHAN_VIEN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKyThuatHoTroIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where kyThuatHoTro equals to DEFAULT_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldBeFound("kyThuatHoTro.equals=" + DEFAULT_KY_THUAT_HO_TRO);

        // Get all the chiDaoTuyenList where kyThuatHoTro equals to UPDATED_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("kyThuatHoTro.equals=" + UPDATED_KY_THUAT_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKyThuatHoTroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where kyThuatHoTro not equals to DEFAULT_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("kyThuatHoTro.notEquals=" + DEFAULT_KY_THUAT_HO_TRO);

        // Get all the chiDaoTuyenList where kyThuatHoTro not equals to UPDATED_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldBeFound("kyThuatHoTro.notEquals=" + UPDATED_KY_THUAT_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKyThuatHoTroIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where kyThuatHoTro in DEFAULT_KY_THUAT_HO_TRO or UPDATED_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldBeFound("kyThuatHoTro.in=" + DEFAULT_KY_THUAT_HO_TRO + "," + UPDATED_KY_THUAT_HO_TRO);

        // Get all the chiDaoTuyenList where kyThuatHoTro equals to UPDATED_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("kyThuatHoTro.in=" + UPDATED_KY_THUAT_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKyThuatHoTroIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where kyThuatHoTro is not null
        defaultChiDaoTuyenShouldBeFound("kyThuatHoTro.specified=true");

        // Get all the chiDaoTuyenList where kyThuatHoTro is null
        defaultChiDaoTuyenShouldNotBeFound("kyThuatHoTro.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKyThuatHoTroContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where kyThuatHoTro contains DEFAULT_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldBeFound("kyThuatHoTro.contains=" + DEFAULT_KY_THUAT_HO_TRO);

        // Get all the chiDaoTuyenList where kyThuatHoTro contains UPDATED_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("kyThuatHoTro.contains=" + UPDATED_KY_THUAT_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKyThuatHoTroNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where kyThuatHoTro does not contain DEFAULT_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("kyThuatHoTro.doesNotContain=" + DEFAULT_KY_THUAT_HO_TRO);

        // Get all the chiDaoTuyenList where kyThuatHoTro does not contain UPDATED_KY_THUAT_HO_TRO
        defaultChiDaoTuyenShouldBeFound("kyThuatHoTro.doesNotContain=" + UPDATED_KY_THUAT_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByVatTuHoTroIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where vatTuHoTro equals to DEFAULT_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldBeFound("vatTuHoTro.equals=" + DEFAULT_VAT_TU_HO_TRO);

        // Get all the chiDaoTuyenList where vatTuHoTro equals to UPDATED_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("vatTuHoTro.equals=" + UPDATED_VAT_TU_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByVatTuHoTroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where vatTuHoTro not equals to DEFAULT_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("vatTuHoTro.notEquals=" + DEFAULT_VAT_TU_HO_TRO);

        // Get all the chiDaoTuyenList where vatTuHoTro not equals to UPDATED_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldBeFound("vatTuHoTro.notEquals=" + UPDATED_VAT_TU_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByVatTuHoTroIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where vatTuHoTro in DEFAULT_VAT_TU_HO_TRO or UPDATED_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldBeFound("vatTuHoTro.in=" + DEFAULT_VAT_TU_HO_TRO + "," + UPDATED_VAT_TU_HO_TRO);

        // Get all the chiDaoTuyenList where vatTuHoTro equals to UPDATED_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("vatTuHoTro.in=" + UPDATED_VAT_TU_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByVatTuHoTroIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where vatTuHoTro is not null
        defaultChiDaoTuyenShouldBeFound("vatTuHoTro.specified=true");

        // Get all the chiDaoTuyenList where vatTuHoTro is null
        defaultChiDaoTuyenShouldNotBeFound("vatTuHoTro.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByVatTuHoTroContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where vatTuHoTro contains DEFAULT_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldBeFound("vatTuHoTro.contains=" + DEFAULT_VAT_TU_HO_TRO);

        // Get all the chiDaoTuyenList where vatTuHoTro contains UPDATED_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("vatTuHoTro.contains=" + UPDATED_VAT_TU_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByVatTuHoTroNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where vatTuHoTro does not contain DEFAULT_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldNotBeFound("vatTuHoTro.doesNotContain=" + DEFAULT_VAT_TU_HO_TRO);

        // Get all the chiDaoTuyenList where vatTuHoTro does not contain UPDATED_VAT_TU_HO_TRO
        defaultChiDaoTuyenShouldBeFound("vatTuHoTro.doesNotContain=" + UPDATED_VAT_TU_HO_TRO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnKhamDieuTriIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri equals to DEFAULT_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldBeFound("soBnKhamDieuTri.equals=" + DEFAULT_SO_BN_KHAM_DIEU_TRI);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri equals to UPDATED_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldNotBeFound("soBnKhamDieuTri.equals=" + UPDATED_SO_BN_KHAM_DIEU_TRI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnKhamDieuTriIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri not equals to DEFAULT_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldNotBeFound("soBnKhamDieuTri.notEquals=" + DEFAULT_SO_BN_KHAM_DIEU_TRI);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri not equals to UPDATED_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldBeFound("soBnKhamDieuTri.notEquals=" + UPDATED_SO_BN_KHAM_DIEU_TRI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnKhamDieuTriIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri in DEFAULT_SO_BN_KHAM_DIEU_TRI or UPDATED_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldBeFound("soBnKhamDieuTri.in=" + DEFAULT_SO_BN_KHAM_DIEU_TRI + "," + UPDATED_SO_BN_KHAM_DIEU_TRI);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri equals to UPDATED_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldNotBeFound("soBnKhamDieuTri.in=" + UPDATED_SO_BN_KHAM_DIEU_TRI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnKhamDieuTriIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri is not null
        defaultChiDaoTuyenShouldBeFound("soBnKhamDieuTri.specified=true");

        // Get all the chiDaoTuyenList where soBnKhamDieuTri is null
        defaultChiDaoTuyenShouldNotBeFound("soBnKhamDieuTri.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnKhamDieuTriContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri contains DEFAULT_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldBeFound("soBnKhamDieuTri.contains=" + DEFAULT_SO_BN_KHAM_DIEU_TRI);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri contains UPDATED_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldNotBeFound("soBnKhamDieuTri.contains=" + UPDATED_SO_BN_KHAM_DIEU_TRI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnKhamDieuTriNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri does not contain DEFAULT_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldNotBeFound("soBnKhamDieuTri.doesNotContain=" + DEFAULT_SO_BN_KHAM_DIEU_TRI);

        // Get all the chiDaoTuyenList where soBnKhamDieuTri does not contain UPDATED_SO_BN_KHAM_DIEU_TRI
        defaultChiDaoTuyenShouldBeFound("soBnKhamDieuTri.doesNotContain=" + UPDATED_SO_BN_KHAM_DIEU_TRI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnPhauThuatIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnPhauThuat equals to DEFAULT_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldBeFound("soBnPhauThuat.equals=" + DEFAULT_SO_BN_PHAU_THUAT);

        // Get all the chiDaoTuyenList where soBnPhauThuat equals to UPDATED_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldNotBeFound("soBnPhauThuat.equals=" + UPDATED_SO_BN_PHAU_THUAT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnPhauThuatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnPhauThuat not equals to DEFAULT_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldNotBeFound("soBnPhauThuat.notEquals=" + DEFAULT_SO_BN_PHAU_THUAT);

        // Get all the chiDaoTuyenList where soBnPhauThuat not equals to UPDATED_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldBeFound("soBnPhauThuat.notEquals=" + UPDATED_SO_BN_PHAU_THUAT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnPhauThuatIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnPhauThuat in DEFAULT_SO_BN_PHAU_THUAT or UPDATED_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldBeFound("soBnPhauThuat.in=" + DEFAULT_SO_BN_PHAU_THUAT + "," + UPDATED_SO_BN_PHAU_THUAT);

        // Get all the chiDaoTuyenList where soBnPhauThuat equals to UPDATED_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldNotBeFound("soBnPhauThuat.in=" + UPDATED_SO_BN_PHAU_THUAT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnPhauThuatIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnPhauThuat is not null
        defaultChiDaoTuyenShouldBeFound("soBnPhauThuat.specified=true");

        // Get all the chiDaoTuyenList where soBnPhauThuat is null
        defaultChiDaoTuyenShouldNotBeFound("soBnPhauThuat.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnPhauThuatContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnPhauThuat contains DEFAULT_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldBeFound("soBnPhauThuat.contains=" + DEFAULT_SO_BN_PHAU_THUAT);

        // Get all the chiDaoTuyenList where soBnPhauThuat contains UPDATED_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldNotBeFound("soBnPhauThuat.contains=" + UPDATED_SO_BN_PHAU_THUAT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoBnPhauThuatNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soBnPhauThuat does not contain DEFAULT_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldNotBeFound("soBnPhauThuat.doesNotContain=" + DEFAULT_SO_BN_PHAU_THUAT);

        // Get all the chiDaoTuyenList where soBnPhauThuat does not contain UPDATED_SO_BN_PHAU_THUAT
        defaultChiDaoTuyenShouldBeFound("soBnPhauThuat.doesNotContain=" + UPDATED_SO_BN_PHAU_THUAT);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoCanBoChuyenGiaoIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao equals to DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.equals=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao equals to UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.equals=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoCanBoChuyenGiaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao not equals to DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.notEquals=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao not equals to UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.notEquals=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoCanBoChuyenGiaoIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao in DEFAULT_SO_CAN_BO_CHUYEN_GIAO or UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.in=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO + "," + UPDATED_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao equals to UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.in=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoCanBoChuyenGiaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao is not null
        defaultChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.specified=true");

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao is null
        defaultChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoCanBoChuyenGiaoContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao contains DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.contains=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao contains UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.contains=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensBySoCanBoChuyenGiaoNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao does not contain DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.doesNotContain=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the chiDaoTuyenList where soCanBoChuyenGiao does not contain UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.doesNotContain=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKetQuaCongTacIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ketQuaCongTac equals to DEFAULT_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("ketQuaCongTac.equals=" + DEFAULT_KET_QUA_CONG_TAC);

        // Get all the chiDaoTuyenList where ketQuaCongTac equals to UPDATED_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("ketQuaCongTac.equals=" + UPDATED_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKetQuaCongTacIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ketQuaCongTac not equals to DEFAULT_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("ketQuaCongTac.notEquals=" + DEFAULT_KET_QUA_CONG_TAC);

        // Get all the chiDaoTuyenList where ketQuaCongTac not equals to UPDATED_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("ketQuaCongTac.notEquals=" + UPDATED_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKetQuaCongTacIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ketQuaCongTac in DEFAULT_KET_QUA_CONG_TAC or UPDATED_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("ketQuaCongTac.in=" + DEFAULT_KET_QUA_CONG_TAC + "," + UPDATED_KET_QUA_CONG_TAC);

        // Get all the chiDaoTuyenList where ketQuaCongTac equals to UPDATED_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("ketQuaCongTac.in=" + UPDATED_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKetQuaCongTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ketQuaCongTac is not null
        defaultChiDaoTuyenShouldBeFound("ketQuaCongTac.specified=true");

        // Get all the chiDaoTuyenList where ketQuaCongTac is null
        defaultChiDaoTuyenShouldNotBeFound("ketQuaCongTac.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKetQuaCongTacContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ketQuaCongTac contains DEFAULT_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("ketQuaCongTac.contains=" + DEFAULT_KET_QUA_CONG_TAC);

        // Get all the chiDaoTuyenList where ketQuaCongTac contains UPDATED_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("ketQuaCongTac.contains=" + UPDATED_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKetQuaCongTacNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where ketQuaCongTac does not contain DEFAULT_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldNotBeFound("ketQuaCongTac.doesNotContain=" + DEFAULT_KET_QUA_CONG_TAC);

        // Get all the chiDaoTuyenList where ketQuaCongTac does not contain UPDATED_KET_QUA_CONG_TAC
        defaultChiDaoTuyenShouldBeFound("ketQuaCongTac.doesNotContain=" + UPDATED_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLuuTruIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where luuTru equals to DEFAULT_LUU_TRU
        defaultChiDaoTuyenShouldBeFound("luuTru.equals=" + DEFAULT_LUU_TRU);

        // Get all the chiDaoTuyenList where luuTru equals to UPDATED_LUU_TRU
        defaultChiDaoTuyenShouldNotBeFound("luuTru.equals=" + UPDATED_LUU_TRU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLuuTruIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where luuTru not equals to DEFAULT_LUU_TRU
        defaultChiDaoTuyenShouldNotBeFound("luuTru.notEquals=" + DEFAULT_LUU_TRU);

        // Get all the chiDaoTuyenList where luuTru not equals to UPDATED_LUU_TRU
        defaultChiDaoTuyenShouldBeFound("luuTru.notEquals=" + UPDATED_LUU_TRU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLuuTruIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where luuTru in DEFAULT_LUU_TRU or UPDATED_LUU_TRU
        defaultChiDaoTuyenShouldBeFound("luuTru.in=" + DEFAULT_LUU_TRU + "," + UPDATED_LUU_TRU);

        // Get all the chiDaoTuyenList where luuTru equals to UPDATED_LUU_TRU
        defaultChiDaoTuyenShouldNotBeFound("luuTru.in=" + UPDATED_LUU_TRU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLuuTruIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where luuTru is not null
        defaultChiDaoTuyenShouldBeFound("luuTru.specified=true");

        // Get all the chiDaoTuyenList where luuTru is null
        defaultChiDaoTuyenShouldNotBeFound("luuTru.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLuuTruContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where luuTru contains DEFAULT_LUU_TRU
        defaultChiDaoTuyenShouldBeFound("luuTru.contains=" + DEFAULT_LUU_TRU);

        // Get all the chiDaoTuyenList where luuTru contains UPDATED_LUU_TRU
        defaultChiDaoTuyenShouldNotBeFound("luuTru.contains=" + UPDATED_LUU_TRU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByLuuTruNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where luuTru does not contain DEFAULT_LUU_TRU
        defaultChiDaoTuyenShouldNotBeFound("luuTru.doesNotContain=" + DEFAULT_LUU_TRU);

        // Get all the chiDaoTuyenList where luuTru does not contain UPDATED_LUU_TRU
        defaultChiDaoTuyenShouldBeFound("luuTru.doesNotContain=" + UPDATED_LUU_TRU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienAnIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienAn equals to DEFAULT_TIEN_AN
        defaultChiDaoTuyenShouldBeFound("tienAn.equals=" + DEFAULT_TIEN_AN);

        // Get all the chiDaoTuyenList where tienAn equals to UPDATED_TIEN_AN
        defaultChiDaoTuyenShouldNotBeFound("tienAn.equals=" + UPDATED_TIEN_AN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienAnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienAn not equals to DEFAULT_TIEN_AN
        defaultChiDaoTuyenShouldNotBeFound("tienAn.notEquals=" + DEFAULT_TIEN_AN);

        // Get all the chiDaoTuyenList where tienAn not equals to UPDATED_TIEN_AN
        defaultChiDaoTuyenShouldBeFound("tienAn.notEquals=" + UPDATED_TIEN_AN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienAnIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienAn in DEFAULT_TIEN_AN or UPDATED_TIEN_AN
        defaultChiDaoTuyenShouldBeFound("tienAn.in=" + DEFAULT_TIEN_AN + "," + UPDATED_TIEN_AN);

        // Get all the chiDaoTuyenList where tienAn equals to UPDATED_TIEN_AN
        defaultChiDaoTuyenShouldNotBeFound("tienAn.in=" + UPDATED_TIEN_AN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienAnIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienAn is not null
        defaultChiDaoTuyenShouldBeFound("tienAn.specified=true");

        // Get all the chiDaoTuyenList where tienAn is null
        defaultChiDaoTuyenShouldNotBeFound("tienAn.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienAnContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienAn contains DEFAULT_TIEN_AN
        defaultChiDaoTuyenShouldBeFound("tienAn.contains=" + DEFAULT_TIEN_AN);

        // Get all the chiDaoTuyenList where tienAn contains UPDATED_TIEN_AN
        defaultChiDaoTuyenShouldNotBeFound("tienAn.contains=" + UPDATED_TIEN_AN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienAnNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienAn does not contain DEFAULT_TIEN_AN
        defaultChiDaoTuyenShouldNotBeFound("tienAn.doesNotContain=" + DEFAULT_TIEN_AN);

        // Get all the chiDaoTuyenList where tienAn does not contain UPDATED_TIEN_AN
        defaultChiDaoTuyenShouldBeFound("tienAn.doesNotContain=" + UPDATED_TIEN_AN);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienOIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienO equals to DEFAULT_TIEN_O
        defaultChiDaoTuyenShouldBeFound("tienO.equals=" + DEFAULT_TIEN_O);

        // Get all the chiDaoTuyenList where tienO equals to UPDATED_TIEN_O
        defaultChiDaoTuyenShouldNotBeFound("tienO.equals=" + UPDATED_TIEN_O);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienOIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienO not equals to DEFAULT_TIEN_O
        defaultChiDaoTuyenShouldNotBeFound("tienO.notEquals=" + DEFAULT_TIEN_O);

        // Get all the chiDaoTuyenList where tienO not equals to UPDATED_TIEN_O
        defaultChiDaoTuyenShouldBeFound("tienO.notEquals=" + UPDATED_TIEN_O);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienOIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienO in DEFAULT_TIEN_O or UPDATED_TIEN_O
        defaultChiDaoTuyenShouldBeFound("tienO.in=" + DEFAULT_TIEN_O + "," + UPDATED_TIEN_O);

        // Get all the chiDaoTuyenList where tienO equals to UPDATED_TIEN_O
        defaultChiDaoTuyenShouldNotBeFound("tienO.in=" + UPDATED_TIEN_O);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienOIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienO is not null
        defaultChiDaoTuyenShouldBeFound("tienO.specified=true");

        // Get all the chiDaoTuyenList where tienO is null
        defaultChiDaoTuyenShouldNotBeFound("tienO.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienOContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienO contains DEFAULT_TIEN_O
        defaultChiDaoTuyenShouldBeFound("tienO.contains=" + DEFAULT_TIEN_O);

        // Get all the chiDaoTuyenList where tienO contains UPDATED_TIEN_O
        defaultChiDaoTuyenShouldNotBeFound("tienO.contains=" + UPDATED_TIEN_O);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienONotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienO does not contain DEFAULT_TIEN_O
        defaultChiDaoTuyenShouldNotBeFound("tienO.doesNotContain=" + DEFAULT_TIEN_O);

        // Get all the chiDaoTuyenList where tienO does not contain UPDATED_TIEN_O
        defaultChiDaoTuyenShouldBeFound("tienO.doesNotContain=" + UPDATED_TIEN_O);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienDiLaiIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienDiLai equals to DEFAULT_TIEN_DI_LAI
        defaultChiDaoTuyenShouldBeFound("tienDiLai.equals=" + DEFAULT_TIEN_DI_LAI);

        // Get all the chiDaoTuyenList where tienDiLai equals to UPDATED_TIEN_DI_LAI
        defaultChiDaoTuyenShouldNotBeFound("tienDiLai.equals=" + UPDATED_TIEN_DI_LAI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienDiLaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienDiLai not equals to DEFAULT_TIEN_DI_LAI
        defaultChiDaoTuyenShouldNotBeFound("tienDiLai.notEquals=" + DEFAULT_TIEN_DI_LAI);

        // Get all the chiDaoTuyenList where tienDiLai not equals to UPDATED_TIEN_DI_LAI
        defaultChiDaoTuyenShouldBeFound("tienDiLai.notEquals=" + UPDATED_TIEN_DI_LAI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienDiLaiIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienDiLai in DEFAULT_TIEN_DI_LAI or UPDATED_TIEN_DI_LAI
        defaultChiDaoTuyenShouldBeFound("tienDiLai.in=" + DEFAULT_TIEN_DI_LAI + "," + UPDATED_TIEN_DI_LAI);

        // Get all the chiDaoTuyenList where tienDiLai equals to UPDATED_TIEN_DI_LAI
        defaultChiDaoTuyenShouldNotBeFound("tienDiLai.in=" + UPDATED_TIEN_DI_LAI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienDiLaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienDiLai is not null
        defaultChiDaoTuyenShouldBeFound("tienDiLai.specified=true");

        // Get all the chiDaoTuyenList where tienDiLai is null
        defaultChiDaoTuyenShouldNotBeFound("tienDiLai.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienDiLaiContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienDiLai contains DEFAULT_TIEN_DI_LAI
        defaultChiDaoTuyenShouldBeFound("tienDiLai.contains=" + DEFAULT_TIEN_DI_LAI);

        // Get all the chiDaoTuyenList where tienDiLai contains UPDATED_TIEN_DI_LAI
        defaultChiDaoTuyenShouldNotBeFound("tienDiLai.contains=" + UPDATED_TIEN_DI_LAI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTienDiLaiNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where tienDiLai does not contain DEFAULT_TIEN_DI_LAI
        defaultChiDaoTuyenShouldNotBeFound("tienDiLai.doesNotContain=" + DEFAULT_TIEN_DI_LAI);

        // Get all the chiDaoTuyenList where tienDiLai does not contain UPDATED_TIEN_DI_LAI
        defaultChiDaoTuyenShouldBeFound("tienDiLai.doesNotContain=" + UPDATED_TIEN_DI_LAI);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTaiLieuIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where taiLieu equals to DEFAULT_TAI_LIEU
        defaultChiDaoTuyenShouldBeFound("taiLieu.equals=" + DEFAULT_TAI_LIEU);

        // Get all the chiDaoTuyenList where taiLieu equals to UPDATED_TAI_LIEU
        defaultChiDaoTuyenShouldNotBeFound("taiLieu.equals=" + UPDATED_TAI_LIEU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTaiLieuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where taiLieu not equals to DEFAULT_TAI_LIEU
        defaultChiDaoTuyenShouldNotBeFound("taiLieu.notEquals=" + DEFAULT_TAI_LIEU);

        // Get all the chiDaoTuyenList where taiLieu not equals to UPDATED_TAI_LIEU
        defaultChiDaoTuyenShouldBeFound("taiLieu.notEquals=" + UPDATED_TAI_LIEU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTaiLieuIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where taiLieu in DEFAULT_TAI_LIEU or UPDATED_TAI_LIEU
        defaultChiDaoTuyenShouldBeFound("taiLieu.in=" + DEFAULT_TAI_LIEU + "," + UPDATED_TAI_LIEU);

        // Get all the chiDaoTuyenList where taiLieu equals to UPDATED_TAI_LIEU
        defaultChiDaoTuyenShouldNotBeFound("taiLieu.in=" + UPDATED_TAI_LIEU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTaiLieuIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where taiLieu is not null
        defaultChiDaoTuyenShouldBeFound("taiLieu.specified=true");

        // Get all the chiDaoTuyenList where taiLieu is null
        defaultChiDaoTuyenShouldNotBeFound("taiLieu.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTaiLieuContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where taiLieu contains DEFAULT_TAI_LIEU
        defaultChiDaoTuyenShouldBeFound("taiLieu.contains=" + DEFAULT_TAI_LIEU);

        // Get all the chiDaoTuyenList where taiLieu contains UPDATED_TAI_LIEU
        defaultChiDaoTuyenShouldNotBeFound("taiLieu.contains=" + UPDATED_TAI_LIEU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByTaiLieuNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where taiLieu does not contain DEFAULT_TAI_LIEU
        defaultChiDaoTuyenShouldNotBeFound("taiLieu.doesNotContain=" + DEFAULT_TAI_LIEU);

        // Get all the chiDaoTuyenList where taiLieu does not contain UPDATED_TAI_LIEU
        defaultChiDaoTuyenShouldBeFound("taiLieu.doesNotContain=" + UPDATED_TAI_LIEU);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGiangDayIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where giangDay equals to DEFAULT_GIANG_DAY
        defaultChiDaoTuyenShouldBeFound("giangDay.equals=" + DEFAULT_GIANG_DAY);

        // Get all the chiDaoTuyenList where giangDay equals to UPDATED_GIANG_DAY
        defaultChiDaoTuyenShouldNotBeFound("giangDay.equals=" + UPDATED_GIANG_DAY);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGiangDayIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where giangDay not equals to DEFAULT_GIANG_DAY
        defaultChiDaoTuyenShouldNotBeFound("giangDay.notEquals=" + DEFAULT_GIANG_DAY);

        // Get all the chiDaoTuyenList where giangDay not equals to UPDATED_GIANG_DAY
        defaultChiDaoTuyenShouldBeFound("giangDay.notEquals=" + UPDATED_GIANG_DAY);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGiangDayIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where giangDay in DEFAULT_GIANG_DAY or UPDATED_GIANG_DAY
        defaultChiDaoTuyenShouldBeFound("giangDay.in=" + DEFAULT_GIANG_DAY + "," + UPDATED_GIANG_DAY);

        // Get all the chiDaoTuyenList where giangDay equals to UPDATED_GIANG_DAY
        defaultChiDaoTuyenShouldNotBeFound("giangDay.in=" + UPDATED_GIANG_DAY);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGiangDayIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where giangDay is not null
        defaultChiDaoTuyenShouldBeFound("giangDay.specified=true");

        // Get all the chiDaoTuyenList where giangDay is null
        defaultChiDaoTuyenShouldNotBeFound("giangDay.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGiangDayContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where giangDay contains DEFAULT_GIANG_DAY
        defaultChiDaoTuyenShouldBeFound("giangDay.contains=" + DEFAULT_GIANG_DAY);

        // Get all the chiDaoTuyenList where giangDay contains UPDATED_GIANG_DAY
        defaultChiDaoTuyenShouldNotBeFound("giangDay.contains=" + UPDATED_GIANG_DAY);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByGiangDayNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where giangDay does not contain DEFAULT_GIANG_DAY
        defaultChiDaoTuyenShouldNotBeFound("giangDay.doesNotContain=" + DEFAULT_GIANG_DAY);

        // Get all the chiDaoTuyenList where giangDay does not contain UPDATED_GIANG_DAY
        defaultChiDaoTuyenShouldBeFound("giangDay.doesNotContain=" + UPDATED_GIANG_DAY);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKhacIsEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where khac equals to DEFAULT_KHAC
        defaultChiDaoTuyenShouldBeFound("khac.equals=" + DEFAULT_KHAC);

        // Get all the chiDaoTuyenList where khac equals to UPDATED_KHAC
        defaultChiDaoTuyenShouldNotBeFound("khac.equals=" + UPDATED_KHAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKhacIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where khac not equals to DEFAULT_KHAC
        defaultChiDaoTuyenShouldNotBeFound("khac.notEquals=" + DEFAULT_KHAC);

        // Get all the chiDaoTuyenList where khac not equals to UPDATED_KHAC
        defaultChiDaoTuyenShouldBeFound("khac.notEquals=" + UPDATED_KHAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKhacIsInShouldWork() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where khac in DEFAULT_KHAC or UPDATED_KHAC
        defaultChiDaoTuyenShouldBeFound("khac.in=" + DEFAULT_KHAC + "," + UPDATED_KHAC);

        // Get all the chiDaoTuyenList where khac equals to UPDATED_KHAC
        defaultChiDaoTuyenShouldNotBeFound("khac.in=" + UPDATED_KHAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKhacIsNullOrNotNull() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where khac is not null
        defaultChiDaoTuyenShouldBeFound("khac.specified=true");

        // Get all the chiDaoTuyenList where khac is null
        defaultChiDaoTuyenShouldNotBeFound("khac.specified=false");
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKhacContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where khac contains DEFAULT_KHAC
        defaultChiDaoTuyenShouldBeFound("khac.contains=" + DEFAULT_KHAC);

        // Get all the chiDaoTuyenList where khac contains UPDATED_KHAC
        defaultChiDaoTuyenShouldNotBeFound("khac.contains=" + UPDATED_KHAC);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyensByKhacNotContainsSomething() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList where khac does not contain DEFAULT_KHAC
        defaultChiDaoTuyenShouldNotBeFound("khac.doesNotContain=" + DEFAULT_KHAC);

        // Get all the chiDaoTuyenList where khac does not contain UPDATED_KHAC
        defaultChiDaoTuyenShouldBeFound("khac.doesNotContain=" + UPDATED_KHAC);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChiDaoTuyenShouldBeFound(String filter) throws Exception {
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiDaoTuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].soQuyetDinh").value(hasItem(DEFAULT_SO_QUYET_DINH)))
            .andExpect(jsonPath("$.[*].ngayQuyetDinh").value(hasItem(sameInstant(DEFAULT_NGAY_QUYET_DINH))))
            .andExpect(jsonPath("$.[*].soHD").value(hasItem(DEFAULT_SO_HD)))
            .andExpect(jsonPath("$.[*].ngayHD").value(hasItem(sameInstant(DEFAULT_NGAY_HD))))
            .andExpect(jsonPath("$.[*].lyDoCT").value(hasItem(DEFAULT_LY_DO_CT)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].noiCongTac").value(hasItem(DEFAULT_NOI_CONG_TAC)))
            .andExpect(jsonPath("$.[*].ngayBatDau").value(hasItem(sameInstant(DEFAULT_NGAY_BAT_DAU))))
            .andExpect(jsonPath("$.[*].ngayKetThuc").value(hasItem(sameInstant(DEFAULT_NGAY_KET_THUC))))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(sameInstant(DEFAULT_NGAY_TAO))))
            .andExpect(jsonPath("$.[*].nhanVien").value(hasItem(DEFAULT_NHAN_VIEN)))
            .andExpect(jsonPath("$.[*].kyThuatHoTro").value(hasItem(DEFAULT_KY_THUAT_HO_TRO)))
            .andExpect(jsonPath("$.[*].vatTuHoTro").value(hasItem(DEFAULT_VAT_TU_HO_TRO)))
            .andExpect(jsonPath("$.[*].soBnKhamDieuTri").value(hasItem(DEFAULT_SO_BN_KHAM_DIEU_TRI)))
            .andExpect(jsonPath("$.[*].soBnPhauThuat").value(hasItem(DEFAULT_SO_BN_PHAU_THUAT)))
            .andExpect(jsonPath("$.[*].soCanBoChuyenGiao").value(hasItem(DEFAULT_SO_CAN_BO_CHUYEN_GIAO)))
            .andExpect(jsonPath("$.[*].ketQuaCongTac").value(hasItem(DEFAULT_KET_QUA_CONG_TAC)))
            .andExpect(jsonPath("$.[*].luuTru").value(hasItem(DEFAULT_LUU_TRU)))
            .andExpect(jsonPath("$.[*].tienAn").value(hasItem(DEFAULT_TIEN_AN)))
            .andExpect(jsonPath("$.[*].tienO").value(hasItem(DEFAULT_TIEN_O)))
            .andExpect(jsonPath("$.[*].tienDiLai").value(hasItem(DEFAULT_TIEN_DI_LAI)))
            .andExpect(jsonPath("$.[*].taiLieu").value(hasItem(DEFAULT_TAI_LIEU)))
            .andExpect(jsonPath("$.[*].giangDay").value(hasItem(DEFAULT_GIANG_DAY)))
            .andExpect(jsonPath("$.[*].khac").value(hasItem(DEFAULT_KHAC)));

        // Check, that the count call also returns 1
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChiDaoTuyenShouldNotBeFound(String filter) throws Exception {
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingChiDaoTuyen() throws Exception {
        // Get the chiDaoTuyen
        restChiDaoTuyenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewChiDaoTuyen() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();

        // Update the chiDaoTuyen
        ChiDaoTuyen updatedChiDaoTuyen = chiDaoTuyenRepository.findById(chiDaoTuyen.getId()).get();
        // Disconnect from session so that the updates on updatedChiDaoTuyen are not directly saved in db
        em.detach(updatedChiDaoTuyen);
        updatedChiDaoTuyen
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .lyDoCT(UPDATED_LY_DO_CT)
            .noiDung(UPDATED_NOI_DUNG)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ngayBatDau(UPDATED_NGAY_BAT_DAU)
            .ngayKetThuc(UPDATED_NGAY_KET_THUC)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayTao(UPDATED_NGAY_TAO)
            .nhanVien(UPDATED_NHAN_VIEN)
            .kyThuatHoTro(UPDATED_KY_THUAT_HO_TRO)
            .vatTuHoTro(UPDATED_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(UPDATED_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(UPDATED_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(UPDATED_KET_QUA_CONG_TAC)
            .luuTru(UPDATED_LUU_TRU)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .tienDiLai(UPDATED_TIEN_DI_LAI)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY)
            .khac(UPDATED_KHAC);
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(updatedChiDaoTuyen);

        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiDaoTuyenDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isOk());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(UPDATED_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(UPDATED_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(UPDATED_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(UPDATED_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(UPDATED_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(UPDATED_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(UPDATED_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(UPDATED_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testChiDaoTuyen.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testChiDaoTuyen.getNhanVien()).isEqualTo(UPDATED_NHAN_VIEN);
        assertThat(testChiDaoTuyen.getKyThuatHoTro()).isEqualTo(UPDATED_KY_THUAT_HO_TRO);
        assertThat(testChiDaoTuyen.getVatTuHoTro()).isEqualTo(UPDATED_VAT_TU_HO_TRO);
        assertThat(testChiDaoTuyen.getSoBnKhamDieuTri()).isEqualTo(UPDATED_SO_BN_KHAM_DIEU_TRI);
        assertThat(testChiDaoTuyen.getSoBnPhauThuat()).isEqualTo(UPDATED_SO_BN_PHAU_THUAT);
        assertThat(testChiDaoTuyen.getSoCanBoChuyenGiao()).isEqualTo(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testChiDaoTuyen.getKetQuaCongTac()).isEqualTo(UPDATED_KET_QUA_CONG_TAC);
        assertThat(testChiDaoTuyen.getLuuTru()).isEqualTo(UPDATED_LUU_TRU);
        assertThat(testChiDaoTuyen.getTienAn()).isEqualTo(UPDATED_TIEN_AN);
        assertThat(testChiDaoTuyen.getTienO()).isEqualTo(UPDATED_TIEN_O);
        assertThat(testChiDaoTuyen.getTienDiLai()).isEqualTo(UPDATED_TIEN_DI_LAI);
        assertThat(testChiDaoTuyen.getTaiLieu()).isEqualTo(UPDATED_TAI_LIEU);
        assertThat(testChiDaoTuyen.getGiangDay()).isEqualTo(UPDATED_GIANG_DAY);
        assertThat(testChiDaoTuyen.getKhac()).isEqualTo(UPDATED_KHAC);
    }

    @Test
    @Transactional
    void putNonExistingChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiDaoTuyenDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChiDaoTuyenWithPatch() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();

        // Update the chiDaoTuyen using partial update
        ChiDaoTuyen partialUpdatedChiDaoTuyen = new ChiDaoTuyen();
        partialUpdatedChiDaoTuyen.setId(chiDaoTuyen.getId());

        partialUpdatedChiDaoTuyen
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .lyDoCT(UPDATED_LY_DO_CT)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ngayBatDau(UPDATED_NGAY_BAT_DAU)
            .ngayTao(UPDATED_NGAY_TAO)
            .kyThuatHoTro(UPDATED_KY_THUAT_HO_TRO)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO)
            .luuTru(UPDATED_LUU_TRU)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY);

        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiDaoTuyen.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedChiDaoTuyen))
            )
            .andExpect(status().isOk());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(DEFAULT_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(DEFAULT_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(UPDATED_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(UPDATED_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(UPDATED_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(UPDATED_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(UPDATED_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(DEFAULT_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testChiDaoTuyen.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testChiDaoTuyen.getNhanVien()).isEqualTo(DEFAULT_NHAN_VIEN);
        assertThat(testChiDaoTuyen.getKyThuatHoTro()).isEqualTo(UPDATED_KY_THUAT_HO_TRO);
        assertThat(testChiDaoTuyen.getVatTuHoTro()).isEqualTo(DEFAULT_VAT_TU_HO_TRO);
        assertThat(testChiDaoTuyen.getSoBnKhamDieuTri()).isEqualTo(DEFAULT_SO_BN_KHAM_DIEU_TRI);
        assertThat(testChiDaoTuyen.getSoBnPhauThuat()).isEqualTo(DEFAULT_SO_BN_PHAU_THUAT);
        assertThat(testChiDaoTuyen.getSoCanBoChuyenGiao()).isEqualTo(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testChiDaoTuyen.getKetQuaCongTac()).isEqualTo(DEFAULT_KET_QUA_CONG_TAC);
        assertThat(testChiDaoTuyen.getLuuTru()).isEqualTo(UPDATED_LUU_TRU);
        assertThat(testChiDaoTuyen.getTienAn()).isEqualTo(UPDATED_TIEN_AN);
        assertThat(testChiDaoTuyen.getTienO()).isEqualTo(UPDATED_TIEN_O);
        assertThat(testChiDaoTuyen.getTienDiLai()).isEqualTo(DEFAULT_TIEN_DI_LAI);
        assertThat(testChiDaoTuyen.getTaiLieu()).isEqualTo(UPDATED_TAI_LIEU);
        assertThat(testChiDaoTuyen.getGiangDay()).isEqualTo(UPDATED_GIANG_DAY);
        assertThat(testChiDaoTuyen.getKhac()).isEqualTo(DEFAULT_KHAC);
    }

    @Test
    @Transactional
    void fullUpdateChiDaoTuyenWithPatch() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();

        // Update the chiDaoTuyen using partial update
        ChiDaoTuyen partialUpdatedChiDaoTuyen = new ChiDaoTuyen();
        partialUpdatedChiDaoTuyen.setId(chiDaoTuyen.getId());

        partialUpdatedChiDaoTuyen
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .lyDoCT(UPDATED_LY_DO_CT)
            .noiDung(UPDATED_NOI_DUNG)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ngayBatDau(UPDATED_NGAY_BAT_DAU)
            .ngayKetThuc(UPDATED_NGAY_KET_THUC)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayTao(UPDATED_NGAY_TAO)
            .nhanVien(UPDATED_NHAN_VIEN)
            .kyThuatHoTro(UPDATED_KY_THUAT_HO_TRO)
            .vatTuHoTro(UPDATED_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(UPDATED_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(UPDATED_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(UPDATED_KET_QUA_CONG_TAC)
            .luuTru(UPDATED_LUU_TRU)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .tienDiLai(UPDATED_TIEN_DI_LAI)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY)
            .khac(UPDATED_KHAC);

        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiDaoTuyen.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedChiDaoTuyen))
            )
            .andExpect(status().isOk());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(UPDATED_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(UPDATED_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(UPDATED_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(UPDATED_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(UPDATED_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(UPDATED_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(UPDATED_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(UPDATED_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testChiDaoTuyen.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testChiDaoTuyen.getNhanVien()).isEqualTo(UPDATED_NHAN_VIEN);
        assertThat(testChiDaoTuyen.getKyThuatHoTro()).isEqualTo(UPDATED_KY_THUAT_HO_TRO);
        assertThat(testChiDaoTuyen.getVatTuHoTro()).isEqualTo(UPDATED_VAT_TU_HO_TRO);
        assertThat(testChiDaoTuyen.getSoBnKhamDieuTri()).isEqualTo(UPDATED_SO_BN_KHAM_DIEU_TRI);
        assertThat(testChiDaoTuyen.getSoBnPhauThuat()).isEqualTo(UPDATED_SO_BN_PHAU_THUAT);
        assertThat(testChiDaoTuyen.getSoCanBoChuyenGiao()).isEqualTo(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testChiDaoTuyen.getKetQuaCongTac()).isEqualTo(UPDATED_KET_QUA_CONG_TAC);
        assertThat(testChiDaoTuyen.getLuuTru()).isEqualTo(UPDATED_LUU_TRU);
        assertThat(testChiDaoTuyen.getTienAn()).isEqualTo(UPDATED_TIEN_AN);
        assertThat(testChiDaoTuyen.getTienO()).isEqualTo(UPDATED_TIEN_O);
        assertThat(testChiDaoTuyen.getTienDiLai()).isEqualTo(UPDATED_TIEN_DI_LAI);
        assertThat(testChiDaoTuyen.getTaiLieu()).isEqualTo(UPDATED_TAI_LIEU);
        assertThat(testChiDaoTuyen.getGiangDay()).isEqualTo(UPDATED_GIANG_DAY);
        assertThat(testChiDaoTuyen.getKhac()).isEqualTo(UPDATED_KHAC);
    }

    @Test
    @Transactional
    void patchNonExistingChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chiDaoTuyenDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChiDaoTuyen() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeDelete = chiDaoTuyenRepository.findAll().size();

        // Delete the chiDaoTuyen
        restChiDaoTuyenMockMvc
            .perform(delete(ENTITY_API_URL_ID, chiDaoTuyen.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
