package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.LyDoCongTac;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link LyDoCongTac}.
 */
public interface LyDoCongTacService {
    /**
     * Save a lyDoCongTac.
     *
     * @param lyDoCongTac the entity to save.
     * @return the persisted entity.
     */
    LyDoCongTac save(LyDoCongTac lyDoCongTac);

    /**
     * Updates a lyDoCongTac.
     *
     * @param lyDoCongTac the entity to update.
     * @return the persisted entity.
     */
    LyDoCongTac update(LyDoCongTac lyDoCongTac);

    /**
     * Partially updates a lyDoCongTac.
     *
     * @param lyDoCongTac the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LyDoCongTac> partialUpdate(LyDoCongTac lyDoCongTac);

    /**
     * Get all the lyDoCongTacs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LyDoCongTac> findAll(Pageable pageable);

    /**
     * Get the "id" lyDoCongTac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LyDoCongTac> findOne(Long id);

    /**
     * Delete the "id" lyDoCongTac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
