package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.LyDoCongTac}.
 */
public interface LyDoCongTacService {
    /**
     * Save a lyDoCongTac.
     *
     * @param lyDoCongTacDTO the entity to save.
     * @return the persisted entity.
     */
    LyDoCongTacDTO save(LyDoCongTacDTO lyDoCongTacDTO);

    /**
     * Updates a lyDoCongTac.
     *
     * @param lyDoCongTacDTO the entity to update.
     * @return the persisted entity.
     */
    LyDoCongTacDTO update(LyDoCongTacDTO lyDoCongTacDTO);

    /**
     * Partially updates a lyDoCongTac.
     *
     * @param lyDoCongTacDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LyDoCongTacDTO> partialUpdate(LyDoCongTacDTO lyDoCongTacDTO);

    /**
     * Get all the lyDoCongTacs.
     *
     * @return the list of entities.
     */
    List<LyDoCongTacDTO> findAll();

    /**
     * Get the "id" lyDoCongTac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LyDoCongTacDTO> findOne(Long id);

    /**
     * Delete the "id" lyDoCongTac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
