package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.NoiDenCongTac}.
 */
public interface NoiDenCongTacService {
    /**
     * Save a noiDenCongTac.
     *
     * @param noiDenCongTacDTO the entity to save.
     * @return the persisted entity.
     */
    NoiDenCongTacDTO save(NoiDenCongTacDTO noiDenCongTacDTO);

    /**
     * Updates a noiDenCongTac.
     *
     * @param noiDenCongTacDTO the entity to update.
     * @return the persisted entity.
     */
    NoiDenCongTacDTO update(NoiDenCongTacDTO noiDenCongTacDTO);

    /**
     * Partially updates a noiDenCongTac.
     *
     * @param noiDenCongTacDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoiDenCongTacDTO> partialUpdate(NoiDenCongTacDTO noiDenCongTacDTO);

    /**
     * Get all the noiDenCongTacs.
     *
     * @return the list of entities.
     */
    List<NoiDenCongTacDTO> findAll();

    /**
     * Get the "id" noiDenCongTac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoiDenCongTacDTO> findOne(Long id);

    /**
     * Delete the "id" noiDenCongTac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
