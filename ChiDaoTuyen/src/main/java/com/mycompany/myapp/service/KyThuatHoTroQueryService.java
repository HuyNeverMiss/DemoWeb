package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.repository.KyThuatHoTroRepository;
import com.mycompany.myapp.service.criteria.KyThuatHoTroCriteria;
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
 * Service for executing complex queries for {@link KyThuatHoTro} entities in the database.
 * The main input is a {@link KyThuatHoTroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link KyThuatHoTro} or a {@link Page} of {@link KyThuatHoTro} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class KyThuatHoTroQueryService extends QueryService<KyThuatHoTro> {

    private final Logger log = LoggerFactory.getLogger(KyThuatHoTroQueryService.class);

    private final KyThuatHoTroRepository kyThuatHoTroRepository;

    public KyThuatHoTroQueryService(KyThuatHoTroRepository kyThuatHoTroRepository) {
        this.kyThuatHoTroRepository = kyThuatHoTroRepository;
    }

    /**
     * Return a {@link List} of {@link KyThuatHoTro} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<KyThuatHoTro> findByCriteria(KyThuatHoTroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<KyThuatHoTro> specification = createSpecification(criteria);
        return kyThuatHoTroRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link KyThuatHoTro} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<KyThuatHoTro> findByCriteria(KyThuatHoTroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<KyThuatHoTro> specification = createSpecification(criteria);
        return kyThuatHoTroRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(KyThuatHoTroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<KyThuatHoTro> specification = createSpecification(criteria);
        return kyThuatHoTroRepository.count(specification);
    }

    /**
     * Function to convert {@link KyThuatHoTroCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<KyThuatHoTro> createSpecification(KyThuatHoTroCriteria criteria) {
        Specification<KyThuatHoTro> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), KyThuatHoTro_.id));
            }
            if (criteria.getMaKyThuat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaKyThuat(), KyThuatHoTro_.maKyThuat));
            }
            if (criteria.getTenKyThuat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenKyThuat(), KyThuatHoTro_.tenKyThuat));
            }
            if (criteria.getThuTuSX() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThuTuSX(), KyThuatHoTro_.thuTuSX));
            }
            if (criteria.getChiDaoTuyenId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getChiDaoTuyenId(),
                            root -> root.join(KyThuatHoTro_.chiDaoTuyen, JoinType.LEFT).get(ChiDaoTuyen_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
