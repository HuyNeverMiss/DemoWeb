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

    private static final String DEFAULT_SO_HD = "AAAAAAAAAA";
    private static final String UPDATED_SO_HD = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_HD = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_HD = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LY_DO_CT = "AAAAAAAAAA";
    private static final String UPDATED_LY_DO_CT = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_CONG_TAC = "AAAAAAAAAA";
    private static final String UPDATED_NOI_CONG_TAC = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_BAT_DAU = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_BAT_DAU = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NGAY_KET_THUC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_KET_THUC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_TAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_TAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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
