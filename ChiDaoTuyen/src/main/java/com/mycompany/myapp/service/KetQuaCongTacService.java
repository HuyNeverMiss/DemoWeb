package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.KetQuaCongTac}.
 */
public interface KetQuaCongTacService {
    /**
     * Save a ketQuaCongTac.
     *
     * @param ketQuaCongTacDTO the entity to save.
     * @return the persisted entity.
     */
    KetQuaCongTacDTO save(KetQuaCongTacDTO ketQuaCongTacDTO);

    /**
     * Updates a ketQuaCongTac.
     *
     * @param ketQuaCongTacDTO the entity to update.
     * @return the persisted entity.
     */
    KetQuaCongTacDTO update(KetQuaCongTacDTO ketQuaCongTacDTO);

    /**
     * Partially updates a ketQuaCongTac.
     *
     * @param ketQuaCongTacDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<KetQuaCongTacDTO> partialUpdate(KetQuaCongTacDTO ketQuaCongTacDTO);

    /**
     * Get all the ketQuaCongTacs.
     *
     * @return the list of entities.
     */
    List<KetQuaCongTacDTO> findAll();

    /**
     * Get the "id" ketQuaCongTac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KetQuaCongTacDTO> findOne(Long id);

    /**
     * Delete the "id" ketQuaCongTac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
