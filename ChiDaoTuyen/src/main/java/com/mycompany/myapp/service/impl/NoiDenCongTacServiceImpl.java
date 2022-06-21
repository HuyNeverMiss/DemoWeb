package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.repository.NoiDenCongTacRepository;
import com.mycompany.myapp.service.NoiDenCongTacService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NoiDenCongTac}.
 */
@Service
@Transactional
public class NoiDenCongTacServiceImpl implements NoiDenCongTacService {

    private final Logger log = LoggerFactory.getLogger(NoiDenCongTacServiceImpl.class);

    private final NoiDenCongTacRepository noiDenCongTacRepository;

    public NoiDenCongTacServiceImpl(NoiDenCongTacRepository noiDenCongTacRepository) {
        this.noiDenCongTacRepository = noiDenCongTacRepository;
    }

    @Override
    public NoiDenCongTac save(NoiDenCongTac noiDenCongTac) {
        log.debug("Request to save NoiDenCongTac : {}", noiDenCongTac);
        return noiDenCongTacRepository.save(noiDenCongTac);
    }

    @Override
    public NoiDenCongTac update(NoiDenCongTac noiDenCongTac) {
        log.debug("Request to save NoiDenCongTac : {}", noiDenCongTac);
        return noiDenCongTacRepository.save(noiDenCongTac);
    }

    @Override
    public Optional<NoiDenCongTac> partialUpdate(NoiDenCongTac noiDenCongTac) {
        log.debug("Request to partially update NoiDenCongTac : {}", noiDenCongTac);

        return noiDenCongTacRepository
            .findById(noiDenCongTac.getId())
            .map(existingNoiDenCongTac -> {
                if (noiDenCongTac.getMaNoiDen() != null) {
                    existingNoiDenCongTac.setMaNoiDen(noiDenCongTac.getMaNoiDen());
                }
                if (noiDenCongTac.getTenNoiDen() != null) {
                    existingNoiDenCongTac.setTenNoiDen(noiDenCongTac.getTenNoiDen());
                }
                if (noiDenCongTac.getThuTuSX() != null) {
                    existingNoiDenCongTac.setThuTuSX(noiDenCongTac.getThuTuSX());
                }

                return existingNoiDenCongTac;
            })
            .map(noiDenCongTacRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoiDenCongTac> findAll(Pageable pageable) {
        log.debug("Request to get all NoiDenCongTacs");
        return noiDenCongTacRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoiDenCongTac> findOne(Long id) {
        log.debug("Request to get NoiDenCongTac : {}", id);
        return noiDenCongTacRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoiDenCongTac : {}", id);
        noiDenCongTacRepository.deleteById(id);
    }
}
