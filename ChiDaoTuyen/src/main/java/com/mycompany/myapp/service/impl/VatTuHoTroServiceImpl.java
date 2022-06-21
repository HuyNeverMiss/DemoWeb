package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.repository.VatTuHoTroRepository;
import com.mycompany.myapp.service.VatTuHoTroService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VatTuHoTro}.
 */
@Service
@Transactional
public class VatTuHoTroServiceImpl implements VatTuHoTroService {

    private final Logger log = LoggerFactory.getLogger(VatTuHoTroServiceImpl.class);

    private final VatTuHoTroRepository vatTuHoTroRepository;

    public VatTuHoTroServiceImpl(VatTuHoTroRepository vatTuHoTroRepository) {
        this.vatTuHoTroRepository = vatTuHoTroRepository;
    }

    @Override
    public VatTuHoTro save(VatTuHoTro vatTuHoTro) {
        log.debug("Request to save VatTuHoTro : {}", vatTuHoTro);
        return vatTuHoTroRepository.save(vatTuHoTro);
    }

    @Override
    public VatTuHoTro update(VatTuHoTro vatTuHoTro) {
        log.debug("Request to save VatTuHoTro : {}", vatTuHoTro);
        return vatTuHoTroRepository.save(vatTuHoTro);
    }

    @Override
    public Optional<VatTuHoTro> partialUpdate(VatTuHoTro vatTuHoTro) {
        log.debug("Request to partially update VatTuHoTro : {}", vatTuHoTro);

        return vatTuHoTroRepository
            .findById(vatTuHoTro.getId())
            .map(existingVatTuHoTro -> {
                if (vatTuHoTro.getMaVatTu() != null) {
                    existingVatTuHoTro.setMaVatTu(vatTuHoTro.getMaVatTu());
                }
                if (vatTuHoTro.getTenVatTu() != null) {
                    existingVatTuHoTro.setTenVatTu(vatTuHoTro.getTenVatTu());
                }
                if (vatTuHoTro.getThuTuSX() != null) {
                    existingVatTuHoTro.setThuTuSX(vatTuHoTro.getThuTuSX());
                }

                return existingVatTuHoTro;
            })
            .map(vatTuHoTroRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VatTuHoTro> findAll(Pageable pageable) {
        log.debug("Request to get all VatTuHoTros");
        return vatTuHoTroRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VatTuHoTro> findOne(Long id) {
        log.debug("Request to get VatTuHoTro : {}", id);
        return vatTuHoTroRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VatTuHoTro : {}", id);
        vatTuHoTroRepository.deleteById(id);
    }
}
