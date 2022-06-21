package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.KetQuaCongTac;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link KetQuaCongTac}.
 */
public interface KetQuaCongTacService {
    /**
     * Save a ketQuaCongTac.
     *
     * @param ketQuaCongTac the entity to save.
     * @return the persisted entity.
     */
    KetQuaCongTac save(KetQuaCongTac ketQuaCongTac);

    /**
     * Updates a ketQuaCongTac.
     *
     * @param ketQuaCongTac the entity to update.
     * @return the persisted entity.
     */
    KetQuaCongTac update(KetQuaCongTac ketQuaCongTac);

    /**
     * Partially updates a ketQuaCongTac.
     *
     * @param ketQuaCongTac the entity to update partially.
     * @return the persisted entity.
     */
    Optional<KetQuaCongTac> partialUpdate(KetQuaCongTac ketQuaCongTac);

    /**
     * Get all the ketQuaCongTacs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KetQuaCongTac> findAll(Pageable pageable);

    /**
     * Get the "id" ketQuaCongTac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KetQuaCongTac> findOne(Long id);

    /**
     * Delete the "id" ketQuaCongTac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
