package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.repository.ChiDaoTuyenRepository;
import com.mycompany.myapp.service.ChiDaoTuyenService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ChiDaoTuyen}.
 */
@Service
@Transactional
public class ChiDaoTuyenServiceImpl implements ChiDaoTuyenService {

    private final Logger log = LoggerFactory.getLogger(ChiDaoTuyenServiceImpl.class);

    private final ChiDaoTuyenRepository chiDaoTuyenRepository;

    public ChiDaoTuyenServiceImpl(ChiDaoTuyenRepository chiDaoTuyenRepository) {
        this.chiDaoTuyenRepository = chiDaoTuyenRepository;
    }

    @Override
    public ChiDaoTuyen save(ChiDaoTuyen chiDaoTuyen) {
        log.debug("Request to save ChiDaoTuyen : {}", chiDaoTuyen);
        return chiDaoTuyenRepository.save(chiDaoTuyen);
    }

    @Override
    public ChiDaoTuyen update(ChiDaoTuyen chiDaoTuyen) {
        log.debug("Request to save ChiDaoTuyen : {}", chiDaoTuyen);
        return chiDaoTuyenRepository.save(chiDaoTuyen);
    }

    @Override
    public Optional<ChiDaoTuyen> partialUpdate(ChiDaoTuyen chiDaoTuyen) {
        log.debug("Request to partially update ChiDaoTuyen : {}", chiDaoTuyen);

        return chiDaoTuyenRepository
            .findById(chiDaoTuyen.getId())
            .map(existingChiDaoTuyen -> {
                if (chiDaoTuyen.getSoQuyetDinh() != null) {
                    existingChiDaoTuyen.setSoQuyetDinh(chiDaoTuyen.getSoQuyetDinh());
                }
                if (chiDaoTuyen.getNgayQuyetDinh() != null) {
                    existingChiDaoTuyen.setNgayQuyetDinh(chiDaoTuyen.getNgayQuyetDinh());
                }
                if (chiDaoTuyen.getSoHD() != null) {
                    existingChiDaoTuyen.setSoHD(chiDaoTuyen.getSoHD());
                }
                if (chiDaoTuyen.getNgayHD() != null) {
                    existingChiDaoTuyen.setNgayHD(chiDaoTuyen.getNgayHD());
                }
                if (chiDaoTuyen.getLyDoCongTac() != null) {
                    existingChiDaoTuyen.setLyDoCongTac(chiDaoTuyen.getLyDoCongTac());
                }
                if (chiDaoTuyen.getNoiDung() != null) {
                    existingChiDaoTuyen.setNoiDung(chiDaoTuyen.getNoiDung());
                }
                if (chiDaoTuyen.getNoiDenCongTac() != null) {
                    existingChiDaoTuyen.setNoiDenCongTac(chiDaoTuyen.getNoiDenCongTac());
                }
                if (chiDaoTuyen.getNgayBatDau() != null) {
                    existingChiDaoTuyen.setNgayBatDau(chiDaoTuyen.getNgayBatDau());
                }
                if (chiDaoTuyen.getNgayKetThuc() != null) {
                    existingChiDaoTuyen.setNgayKetThuc(chiDaoTuyen.getNgayKetThuc());
                }
                if (chiDaoTuyen.getGhiChu() != null) {
                    existingChiDaoTuyen.setGhiChu(chiDaoTuyen.getGhiChu());
                }
                if (chiDaoTuyen.getNgayTao() != null) {
                    existingChiDaoTuyen.setNgayTao(chiDaoTuyen.getNgayTao());
                }
                if (chiDaoTuyen.getNhanVien() != null) {
                    existingChiDaoTuyen.setNhanVien(chiDaoTuyen.getNhanVien());
                }
                if (chiDaoTuyen.getKyThuatHoTro() != null) {
                    existingChiDaoTuyen.setKyThuatHoTro(chiDaoTuyen.getKyThuatHoTro());
                }
                if (chiDaoTuyen.getVatTuHoTro() != null) {
                    existingChiDaoTuyen.setVatTuHoTro(chiDaoTuyen.getVatTuHoTro());
                }
                if (chiDaoTuyen.getSoBnKhamDieuTri() != null) {
                    existingChiDaoTuyen.setSoBnKhamDieuTri(chiDaoTuyen.getSoBnKhamDieuTri());
                }
                if (chiDaoTuyen.getSoBnPhauThuat() != null) {
                    existingChiDaoTuyen.setSoBnPhauThuat(chiDaoTuyen.getSoBnPhauThuat());
                }
                if (chiDaoTuyen.getSoCanBoChuyenGiao() != null) {
                    existingChiDaoTuyen.setSoCanBoChuyenGiao(chiDaoTuyen.getSoCanBoChuyenGiao());
                }
                if (chiDaoTuyen.getKetQuaCongTac() != null) {
                    existingChiDaoTuyen.setKetQuaCongTac(chiDaoTuyen.getKetQuaCongTac());
                }
                if (chiDaoTuyen.getLuuTru() != null) {
                    existingChiDaoTuyen.setLuuTru(chiDaoTuyen.getLuuTru());
                }
                if (chiDaoTuyen.getTienAn() != null) {
                    existingChiDaoTuyen.setTienAn(chiDaoTuyen.getTienAn());
                }
                if (chiDaoTuyen.getTienO() != null) {
                    existingChiDaoTuyen.setTienO(chiDaoTuyen.getTienO());
                }
                if (chiDaoTuyen.getTienDiLai() != null) {
                    existingChiDaoTuyen.setTienDiLai(chiDaoTuyen.getTienDiLai());
                }
                if (chiDaoTuyen.getTaiLieu() != null) {
                    existingChiDaoTuyen.setTaiLieu(chiDaoTuyen.getTaiLieu());
                }
                if (chiDaoTuyen.getGiangDay() != null) {
                    existingChiDaoTuyen.setGiangDay(chiDaoTuyen.getGiangDay());
                }
                if (chiDaoTuyen.getKhac() != null) {
                    existingChiDaoTuyen.setKhac(chiDaoTuyen.getKhac());
                }

                return existingChiDaoTuyen;
            })
            .map(chiDaoTuyenRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChiDaoTuyen> findAll(Pageable pageable) {
        log.debug("Request to get all ChiDaoTuyens");
        return chiDaoTuyenRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChiDaoTuyen> findOne(Long id) {
        log.debug("Request to get ChiDaoTuyen : {}", id);
        return chiDaoTuyenRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChiDaoTuyen : {}", id);
        chiDaoTuyenRepository.deleteById(id);
    }
}
