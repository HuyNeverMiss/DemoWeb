package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.VatTuHoTro;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link VatTuHoTro}.
 */
public interface VatTuHoTroService {
    /**
     * Save a vatTuHoTro.
     *
     * @param vatTuHoTro the entity to save.
     * @return the persisted entity.
     */
    VatTuHoTro save(VatTuHoTro vatTuHoTro);

    /**
     * Updates a vatTuHoTro.
     *
     * @param vatTuHoTro the entity to update.
     * @return the persisted entity.
     */
    VatTuHoTro update(VatTuHoTro vatTuHoTro);

    /**
     * Partially updates a vatTuHoTro.
     *
     * @param vatTuHoTro the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VatTuHoTro> partialUpdate(VatTuHoTro vatTuHoTro);

    /**
     * Get all the vatTuHoTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VatTuHoTro> findAll(Pageable pageable);

    /**
     * Get the "id" vatTuHoTro.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VatTuHoTro> findOne(Long id);

    /**
     * Delete the "id" vatTuHoTro.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
