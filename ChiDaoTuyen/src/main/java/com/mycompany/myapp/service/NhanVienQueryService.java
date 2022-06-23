package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.NhanVien;
import com.mycompany.myapp.repository.NhanVienRepository;
import com.mycompany.myapp.service.criteria.NhanVienCriteria;
import com.mycompany.myapp.service.dto.NhanVienDTO;
import com.mycompany.myapp.service.mapper.NhanVienMapper;
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
 * Service for executing complex queries for {@link NhanVien} entities in the database.
 * The main input is a {@link NhanVienCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NhanVienDTO} or a {@link Page} of {@link NhanVienDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NhanVienQueryService extends QueryService<NhanVien> {

    private final Logger log = LoggerFactory.getLogger(NhanVienQueryService.class);

    private final NhanVienRepository nhanVienRepository;

    private final NhanVienMapper nhanVienMapper;

    public NhanVienQueryService(NhanVienRepository nhanVienRepository, NhanVienMapper nhanVienMapper) {
        this.nhanVienRepository = nhanVienRepository;
        this.nhanVienMapper = nhanVienMapper;
    }

    /**
     * Return a {@link List} of {@link NhanVienDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NhanVienDTO> findByCriteria(NhanVienCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NhanVien> specification = createSpecification(criteria);
        return nhanVienMapper.toDto(nhanVienRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NhanVienDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NhanVienDTO> findByCriteria(NhanVienCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NhanVien> specification = createSpecification(criteria);
        return nhanVienRepository.findAll(specification, page).map(nhanVienMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NhanVienCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NhanVien> specification = createSpecification(criteria);
        return nhanVienRepository.count(specification);
    }

    /**
     * Function to convert {@link NhanVienCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NhanVien> createSpecification(NhanVienCriteria criteria) {
        Specification<NhanVien> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NhanVien_.id));
            }
            if (criteria.getMaNhanVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaNhanVien(), NhanVien_.maNhanVien));
            }
            if (criteria.getChucVu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChucVu(), NhanVien_.chucVu));
            }
            if (criteria.getChiDaoTuyenId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getChiDaoTuyenId(),
                            root -> root.join(NhanVien_.chiDaoTuyens, JoinType.LEFT).get(ChiDaoTuyen_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
