package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.repository.NoiDenCongTacRepository;
import com.mycompany.myapp.service.criteria.NoiDenCongTacCriteria;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import com.mycompany.myapp.service.mapper.NoiDenCongTacMapper;
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
 * Service for executing complex queries for {@link NoiDenCongTac} entities in the database.
 * The main input is a {@link NoiDenCongTacCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NoiDenCongTacDTO} or a {@link Page} of {@link NoiDenCongTacDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NoiDenCongTacQueryService extends QueryService<NoiDenCongTac> {

    private final Logger log = LoggerFactory.getLogger(NoiDenCongTacQueryService.class);

    private final NoiDenCongTacRepository noiDenCongTacRepository;

    private final NoiDenCongTacMapper noiDenCongTacMapper;

    public NoiDenCongTacQueryService(NoiDenCongTacRepository noiDenCongTacRepository, NoiDenCongTacMapper noiDenCongTacMapper) {
        this.noiDenCongTacRepository = noiDenCongTacRepository;
        this.noiDenCongTacMapper = noiDenCongTacMapper;
    }

    /**
     * Return a {@link List} of {@link NoiDenCongTacDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NoiDenCongTacDTO> findByCriteria(NoiDenCongTacCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NoiDenCongTac> specification = createSpecification(criteria);
        return noiDenCongTacMapper.toDto(noiDenCongTacRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NoiDenCongTacDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NoiDenCongTacDTO> findByCriteria(NoiDenCongTacCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NoiDenCongTac> specification = createSpecification(criteria);
        return noiDenCongTacRepository.findAll(specification, page).map(noiDenCongTacMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NoiDenCongTacCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NoiDenCongTac> specification = createSpecification(criteria);
        return noiDenCongTacRepository.count(specification);
    }

    /**
     * Function to convert {@link NoiDenCongTacCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NoiDenCongTac> createSpecification(NoiDenCongTacCriteria criteria) {
        Specification<NoiDenCongTac> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NoiDenCongTac_.id));
            }
            if (criteria.getMaNoiDen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaNoiDen(), NoiDenCongTac_.maNoiDen));
            }
            if (criteria.getTenNoiDen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenNoiDen(), NoiDenCongTac_.tenNoiDen));
            }
            if (criteria.getThuTuSX() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThuTuSX(), NoiDenCongTac_.thuTuSX));
            }
            if (criteria.getChiDaoTuyenId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getChiDaoTuyenId(),
                            root -> root.join(NoiDenCongTac_.chiDaoTuyen, JoinType.LEFT).get(ChiDaoTuyen_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
