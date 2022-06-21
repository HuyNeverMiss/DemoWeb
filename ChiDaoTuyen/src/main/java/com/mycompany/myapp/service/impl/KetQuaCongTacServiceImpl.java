package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.repository.KetQuaCongTacRepository;
import com.mycompany.myapp.service.KetQuaCongTacService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link KetQuaCongTac}.
 */
@Service
@Transactional
public class KetQuaCongTacServiceImpl implements KetQuaCongTacService {

    private final Logger log = LoggerFactory.getLogger(KetQuaCongTacServiceImpl.class);

    private final KetQuaCongTacRepository ketQuaCongTacRepository;

    public KetQuaCongTacServiceImpl(KetQuaCongTacRepository ketQuaCongTacRepository) {
        this.ketQuaCongTacRepository = ketQuaCongTacRepository;
    }

    @Override
    public KetQuaCongTac save(KetQuaCongTac ketQuaCongTac) {
        log.debug("Request to save KetQuaCongTac : {}", ketQuaCongTac);
        return ketQuaCongTacRepository.save(ketQuaCongTac);
    }

    @Override
    public KetQuaCongTac update(KetQuaCongTac ketQuaCongTac) {
        log.debug("Request to save KetQuaCongTac : {}", ketQuaCongTac);
        return ketQuaCongTacRepository.save(ketQuaCongTac);
    }

    @Override
    public Optional<KetQuaCongTac> partialUpdate(KetQuaCongTac ketQuaCongTac) {
        log.debug("Request to partially update KetQuaCongTac : {}", ketQuaCongTac);

        return ketQuaCongTacRepository
            .findById(ketQuaCongTac.getId())
            .map(existingKetQuaCongTac -> {
                if (ketQuaCongTac.getMaKetQua() != null) {
                    existingKetQuaCongTac.setMaKetQua(ketQuaCongTac.getMaKetQua());
                }
                if (ketQuaCongTac.getTenKetQua() != null) {
                    existingKetQuaCongTac.setTenKetQua(ketQuaCongTac.getTenKetQua());
                }
                if (ketQuaCongTac.getThuTuSX() != null) {
                    existingKetQuaCongTac.setThuTuSX(ketQuaCongTac.getThuTuSX());
                }

                return existingKetQuaCongTac;
            })
            .map(ketQuaCongTacRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<KetQuaCongTac> findAll(Pageable pageable) {
        log.debug("Request to get all KetQuaCongTacs");
        return ketQuaCongTacRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KetQuaCongTac> findOne(Long id) {
        log.debug("Request to get KetQuaCongTac : {}", id);
        return ketQuaCongTacRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete KetQuaCongTac : {}", id);
        ketQuaCongTacRepository.deleteById(id);
    }
}
