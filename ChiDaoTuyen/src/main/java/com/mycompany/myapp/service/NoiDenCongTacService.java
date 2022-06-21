package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.NoiDenCongTac;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link NoiDenCongTac}.
 */
public interface NoiDenCongTacService {
    /**
     * Save a noiDenCongTac.
     *
     * @param noiDenCongTac the entity to save.
     * @return the persisted entity.
     */
    NoiDenCongTac save(NoiDenCongTac noiDenCongTac);

    /**
     * Updates a noiDenCongTac.
     *
     * @param noiDenCongTac the entity to update.
     * @return the persisted entity.
     */
    NoiDenCongTac update(NoiDenCongTac noiDenCongTac);

    /**
     * Partially updates a noiDenCongTac.
     *
     * @param noiDenCongTac the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoiDenCongTac> partialUpdate(NoiDenCongTac noiDenCongTac);

    /**
     * Get all the noiDenCongTacs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NoiDenCongTac> findAll(Pageable pageable);

    /**
     * Get the "id" noiDenCongTac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoiDenCongTac> findOne(Long id);

    /**
     * Delete the "id" noiDenCongTac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
