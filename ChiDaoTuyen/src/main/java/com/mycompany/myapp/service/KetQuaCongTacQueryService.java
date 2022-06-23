package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.repository.KetQuaCongTacRepository;
import com.mycompany.myapp.service.criteria.KetQuaCongTacCriteria;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import com.mycompany.myapp.service.mapper.KetQuaCongTacMapper;
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
 * Service for executing complex queries for {@link KetQuaCongTac} entities in the database.
 * The main input is a {@link KetQuaCongTacCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link KetQuaCongTacDTO} or a {@link Page} of {@link KetQuaCongTacDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class KetQuaCongTacQueryService extends QueryService<KetQuaCongTac> {

    private final Logger log = LoggerFactory.getLogger(KetQuaCongTacQueryService.class);

    private final KetQuaCongTacRepository ketQuaCongTacRepository;

    private final KetQuaCongTacMapper ketQuaCongTacMapper;

    public KetQuaCongTacQueryService(KetQuaCongTacRepository ketQuaCongTacRepository, KetQuaCongTacMapper ketQuaCongTacMapper) {
        this.ketQuaCongTacRepository = ketQuaCongTacRepository;
        this.ketQuaCongTacMapper = ketQuaCongTacMapper;
    }

    /**
     * Return a {@link List} of {@link KetQuaCongTacDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<KetQuaCongTacDTO> findByCriteria(KetQuaCongTacCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<KetQuaCongTac> specification = createSpecification(criteria);
        return ketQuaCongTacMapper.toDto(ketQuaCongTacRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link KetQuaCongTacDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<KetQuaCongTacDTO> findByCriteria(KetQuaCongTacCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<KetQuaCongTac> specification = createSpecification(criteria);
        return ketQuaCongTacRepository.findAll(specification, page).map(ketQuaCongTacMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(KetQuaCongTacCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<KetQuaCongTac> specification = createSpecification(criteria);
        return ketQuaCongTacRepository.count(specification);
    }

    /**
     * Function to convert {@link KetQuaCongTacCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<KetQuaCongTac> createSpecification(KetQuaCongTacCriteria criteria) {
        Specification<KetQuaCongTac> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), KetQuaCongTac_.id));
            }
            if (criteria.getMaKetQua() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaKetQua(), KetQuaCongTac_.maKetQua));
            }
            if (criteria.getTenKetQua() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenKetQua(), KetQuaCongTac_.tenKetQua));
            }
            if (criteria.getThuTuSX() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThuTuSX(), KetQuaCongTac_.thuTuSX));
            }
            if (criteria.getChiDaoTuyenId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getChiDaoTuyenId(),
                            root -> root.join(KetQuaCongTac_.chiDaoTuyens, JoinType.LEFT).get(ChiDaoTuyen_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
