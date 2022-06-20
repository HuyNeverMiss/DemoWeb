package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ChiDaoTuyen}.
 */
public interface ChiDaoTuyenService {
    /**
     * Save a chiDaoTuyen.
     *
     * @param chiDaoTuyenDTO the entity to save.
     * @return the persisted entity.
     */
    ChiDaoTuyenDTO save(ChiDaoTuyenDTO chiDaoTuyenDTO);

    /**
     * Updates a chiDaoTuyen.
     *
     * @param chiDaoTuyenDTO the entity to update.
     * @return the persisted entity.
     */
    ChiDaoTuyenDTO update(ChiDaoTuyenDTO chiDaoTuyenDTO);

    /**
     * Partially updates a chiDaoTuyen.
     *
     * @param chiDaoTuyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChiDaoTuyenDTO> partialUpdate(ChiDaoTuyenDTO chiDaoTuyenDTO);

    /**
     * Get all the chiDaoTuyens.
     *
     * @return the list of entities.
     */
    List<ChiDaoTuyenDTO> findAll();

    /**
     * Get the "id" chiDaoTuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChiDaoTuyenDTO> findOne(Long id);

    /**
     * Delete the "id" chiDaoTuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
