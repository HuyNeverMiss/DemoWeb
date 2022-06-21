package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.KyThuatHoTro;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link KyThuatHoTro}.
 */
public interface KyThuatHoTroService {
    /**
     * Save a kyThuatHoTro.
     *
     * @param kyThuatHoTro the entity to save.
     * @return the persisted entity.
     */
    KyThuatHoTro save(KyThuatHoTro kyThuatHoTro);

    /**
     * Updates a kyThuatHoTro.
     *
     * @param kyThuatHoTro the entity to update.
     * @return the persisted entity.
     */
    KyThuatHoTro update(KyThuatHoTro kyThuatHoTro);

    /**
     * Partially updates a kyThuatHoTro.
     *
     * @param kyThuatHoTro the entity to update partially.
     * @return the persisted entity.
     */
    Optional<KyThuatHoTro> partialUpdate(KyThuatHoTro kyThuatHoTro);

    /**
     * Get all the kyThuatHoTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KyThuatHoTro> findAll(Pageable pageable);

    /**
     * Get the "id" kyThuatHoTro.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KyThuatHoTro> findOne(Long id);

    /**
     * Delete the "id" kyThuatHoTro.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
