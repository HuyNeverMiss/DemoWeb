package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.repository.ChiDaoTuyenRepository;
import com.mycompany.myapp.service.criteria.ChiDaoTuyenCriteria;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.mapper.ChiDaoTuyenMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ChiDaoTuyen} entities in the database.
 * The main input is a {@link ChiDaoTuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChiDaoTuyenDTO} or a {@link Page} of {@link ChiDaoTuyenDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChiDaoTuyenQueryService extends QueryService<ChiDaoTuyen> {

    private final Logger log = LoggerFactory.getLogger(ChiDaoTuyenQueryService.class);

    private final ChiDaoTuyenRepository chiDaoTuyenRepository;

    private final ChiDaoTuyenMapper chiDaoTuyenMapper;

    public ChiDaoTuyenQueryService(ChiDaoTuyenRepository chiDaoTuyenRepository, ChiDaoTuyenMapper chiDaoTuyenMapper) {
        this.chiDaoTuyenRepository = chiDaoTuyenRepository;
        this.chiDaoTuyenMapper = chiDaoTuyenMapper;
    }

    /**
     * Return a {@link List} of {@link ChiDaoTuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChiDaoTuyenDTO> findByCriteria(ChiDaoTuyenCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ChiDaoTuyen> specification = createSpecification(criteria);
        return chiDaoTuyenMapper.toDto(chiDaoTuyenRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ChiDaoTuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ChiDaoTuyenDTO> findByCriteria(ChiDaoTuyenCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ChiDaoTuyen> specification = createSpecification(criteria);
        return chiDaoTuyenRepository.findAll(specification, page).map(chiDaoTuyenMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChiDaoTuyenCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ChiDaoTuyen> specification = createSpecification(criteria);
        return chiDaoTuyenRepository.count(specification);
    }

    /**
     * Function to convert {@link ChiDaoTuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ChiDaoTuyen> createSpecification(ChiDaoTuyenCriteria criteria) {
        Specification<ChiDaoTuyen> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ChiDaoTuyen_.id));
            }
            if (criteria.getSoQuyetDinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoQuyetDinh(), ChiDaoTuyen_.soQuyetDinh));
            }
            if (criteria.getNgayQuyetDinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayQuyetDinh(), ChiDaoTuyen_.ngayQuyetDinh));
            }
            if (criteria.getSoHD() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoHD(), ChiDaoTuyen_.soHD));
            }
            if (criteria.getNgayHD() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayHD(), ChiDaoTuyen_.ngayHD));
            }
            if (criteria.getNoiDung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiDung(), ChiDaoTuyen_.noiDung));
            }
            if (criteria.getNgayBatDau() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBatDau(), ChiDaoTuyen_.ngayBatDau));
            }
            if (criteria.getNgayKetThuc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKetThuc(), ChiDaoTuyen_.ngayKetThuc));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), ChiDaoTuyen_.ghiChu));
            }
            if (criteria.getNgayTao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayTao(), ChiDaoTuyen_.ngayTao));
            }
            if (criteria.getSoBnKhamDieuTri() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoBnKhamDieuTri(), ChiDaoTuyen_.soBnKhamDieuTri));
            }
            if (criteria.getSoBnPhauThuat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoBnPhauThuat(), ChiDaoTuyen_.soBnPhauThuat));
            }
            if (criteria.getSoCanBoChuyenGiao() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getSoCanBoChuyenGiao(), ChiDaoTuyen_.soCanBoChuyenGiao));
            }
            if (criteria.getLuuTru() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLuuTru(), ChiDaoTuyen_.luuTru));
            }
            if (criteria.getTienAn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTienAn(), ChiDaoTuyen_.tienAn));
            }
            if (criteria.getTienO() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTienO(), ChiDaoTuyen_.tienO));
            }
            if (criteria.getTienDiLai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTienDiLai(), ChiDaoTuyen_.tienDiLai));
            }
            if (criteria.getTaiLieu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTaiLieu(), ChiDaoTuyen_.taiLieu));
            }
            if (criteria.getGiangDay() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGiangDay(), ChiDaoTuyen_.giangDay));
            }
            if (criteria.getKhac() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKhac(), ChiDaoTuyen_.khac));
            }
            if (criteria.getLyDoCongTacId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getLyDoCongTacId(),
                            root -> root.join(ChiDaoTuyen_.lyDoCongTac, JoinType.LEFT).get(LyDoCongTac_.id)
                        )
                    );
            }
            if (criteria.getNoiDenCongTacId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getNoiDenCongTacId(),
                            root -> root.join(ChiDaoTuyen_.noiDenCongTac, JoinType.LEFT).get(NoiDenCongTac_.id)
                        )
                    );
            }
            if (criteria.getKetQuaCongTacId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getKetQuaCongTacId(),
                            root -> root.join(ChiDaoTuyen_.ketQuaCongTac, JoinType.LEFT).get(KetQuaCongTac_.id)
                        )
                    );
            }
            if (criteria.getKyThuatHoTroId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getKyThuatHoTroId(),
                            root -> root.join(ChiDaoTuyen_.kyThuatHoTro, JoinType.LEFT).get(KyThuatHoTro_.id)
                        )
                    );
            }
            if (criteria.getVatTuHoTroId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getVatTuHoTroId(),
                            root -> root.join(ChiDaoTuyen_.vatTuHoTro, JoinType.LEFT).get(VatTuHoTro_.id)
                        )
                    );
            }
            if (criteria.getNhanVienId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getNhanVienId(),
                            root -> root.join(ChiDaoTuyen_.nhanVien, JoinType.LEFT).get(NhanVien_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
