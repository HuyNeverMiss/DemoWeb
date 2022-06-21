package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link ChiDaoTuyen}.
 */
public interface ChiDaoTuyenService {
    /**
     * Save a chiDaoTuyen.
     *
     * @param chiDaoTuyen the entity to save.
     * @return the persisted entity.
     */
    ChiDaoTuyen save(ChiDaoTuyen chiDaoTuyen);

    /**
     * Updates a chiDaoTuyen.
     *
     * @param chiDaoTuyen the entity to update.
     * @return the persisted entity.
     */
    ChiDaoTuyen update(ChiDaoTuyen chiDaoTuyen);

    /**
     * Partially updates a chiDaoTuyen.
     *
     * @param chiDaoTuyen the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChiDaoTuyen> partialUpdate(ChiDaoTuyen chiDaoTuyen);

    /**
     * Get all the chiDaoTuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChiDaoTuyen> findAll(Pageable pageable);

    /**
     * Get the "id" chiDaoTuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChiDaoTuyen> findOne(Long id);

    /**
     * Delete the "id" chiDaoTuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
