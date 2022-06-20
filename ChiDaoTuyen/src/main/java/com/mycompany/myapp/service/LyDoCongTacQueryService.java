package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.repository.LyDoCongTacRepository;
import com.mycompany.myapp.service.criteria.LyDoCongTacCriteria;
import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import com.mycompany.myapp.service.mapper.LyDoCongTacMapper;
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
 * Service for executing complex queries for {@link LyDoCongTac} entities in the database.
 * The main input is a {@link LyDoCongTacCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LyDoCongTacDTO} or a {@link Page} of {@link LyDoCongTacDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LyDoCongTacQueryService extends QueryService<LyDoCongTac> {

    private final Logger log = LoggerFactory.getLogger(LyDoCongTacQueryService.class);

    private final LyDoCongTacRepository lyDoCongTacRepository;

    private final LyDoCongTacMapper lyDoCongTacMapper;

    public LyDoCongTacQueryService(LyDoCongTacRepository lyDoCongTacRepository, LyDoCongTacMapper lyDoCongTacMapper) {
        this.lyDoCongTacRepository = lyDoCongTacRepository;
        this.lyDoCongTacMapper = lyDoCongTacMapper;
    }

    /**
     * Return a {@link List} of {@link LyDoCongTacDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LyDoCongTacDTO> findByCriteria(LyDoCongTacCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LyDoCongTac> specification = createSpecification(criteria);
        return lyDoCongTacMapper.toDto(lyDoCongTacRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LyDoCongTacDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LyDoCongTacDTO> findByCriteria(LyDoCongTacCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LyDoCongTac> specification = createSpecification(criteria);
        return lyDoCongTacRepository.findAll(specification, page).map(lyDoCongTacMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LyDoCongTacCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LyDoCongTac> specification = createSpecification(criteria);
        return lyDoCongTacRepository.count(specification);
    }

    /**
     * Function to convert {@link LyDoCongTacCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LyDoCongTac> createSpecification(LyDoCongTacCriteria criteria) {
        Specification<LyDoCongTac> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), LyDoCongTac_.id));
            }
            if (criteria.getMaLyDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaLyDo(), LyDoCongTac_.maLyDo));
            }
            if (criteria.getTenLyDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenLyDo(), LyDoCongTac_.tenLyDo));
            }
            if (criteria.getThuTuSX() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThuTuSX(), LyDoCongTac_.thuTuSX));
            }
            if (criteria.getChiDaoTuyenId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getChiDaoTuyenId(),
                            root -> root.join(LyDoCongTac_.chiDaoTuyen, JoinType.LEFT).get(ChiDaoTuyen_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
