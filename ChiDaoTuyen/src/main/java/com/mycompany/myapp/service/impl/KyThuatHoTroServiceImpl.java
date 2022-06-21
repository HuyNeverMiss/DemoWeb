package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.repository.KyThuatHoTroRepository;
import com.mycompany.myapp.service.KyThuatHoTroService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link KyThuatHoTro}.
 */
@Service
@Transactional
public class KyThuatHoTroServiceImpl implements KyThuatHoTroService {

    private final Logger log = LoggerFactory.getLogger(KyThuatHoTroServiceImpl.class);

    private final KyThuatHoTroRepository kyThuatHoTroRepository;

    public KyThuatHoTroServiceImpl(KyThuatHoTroRepository kyThuatHoTroRepository) {
        this.kyThuatHoTroRepository = kyThuatHoTroRepository;
    }

    @Override
    public KyThuatHoTro save(KyThuatHoTro kyThuatHoTro) {
        log.debug("Request to save KyThuatHoTro : {}", kyThuatHoTro);
        return kyThuatHoTroRepository.save(kyThuatHoTro);
    }

    @Override
    public KyThuatHoTro update(KyThuatHoTro kyThuatHoTro) {
        log.debug("Request to save KyThuatHoTro : {}", kyThuatHoTro);
        return kyThuatHoTroRepository.save(kyThuatHoTro);
    }

    @Override
    public Optional<KyThuatHoTro> partialUpdate(KyThuatHoTro kyThuatHoTro) {
        log.debug("Request to partially update KyThuatHoTro : {}", kyThuatHoTro);

        return kyThuatHoTroRepository
            .findById(kyThuatHoTro.getId())
            .map(existingKyThuatHoTro -> {
                if (kyThuatHoTro.getMaKyThuat() != null) {
                    existingKyThuatHoTro.setMaKyThuat(kyThuatHoTro.getMaKyThuat());
                }
                if (kyThuatHoTro.getTenKyThuat() != null) {
                    existingKyThuatHoTro.setTenKyThuat(kyThuatHoTro.getTenKyThuat());
                }
                if (kyThuatHoTro.getThuTuSX() != null) {
                    existingKyThuatHoTro.setThuTuSX(kyThuatHoTro.getThuTuSX());
                }

                return existingKyThuatHoTro;
            })
            .map(kyThuatHoTroRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<KyThuatHoTro> findAll(Pageable pageable) {
        log.debug("Request to get all KyThuatHoTros");
        return kyThuatHoTroRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KyThuatHoTro> findOne(Long id) {
        log.debug("Request to get KyThuatHoTro : {}", id);
        return kyThuatHoTroRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete KyThuatHoTro : {}", id);
        kyThuatHoTroRepository.deleteById(id);
    }
}
