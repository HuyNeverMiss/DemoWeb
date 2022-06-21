package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.repository.LyDoCongTacRepository;
import com.mycompany.myapp.service.LyDoCongTacService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LyDoCongTac}.
 */
@Service
@Transactional
public class LyDoCongTacServiceImpl implements LyDoCongTacService {

    private final Logger log = LoggerFactory.getLogger(LyDoCongTacServiceImpl.class);

    private final LyDoCongTacRepository lyDoCongTacRepository;

    public LyDoCongTacServiceImpl(LyDoCongTacRepository lyDoCongTacRepository) {
        this.lyDoCongTacRepository = lyDoCongTacRepository;
    }

    @Override
    public LyDoCongTac save(LyDoCongTac lyDoCongTac) {
        log.debug("Request to save LyDoCongTac : {}", lyDoCongTac);
        return lyDoCongTacRepository.save(lyDoCongTac);
    }

    @Override
    public LyDoCongTac update(LyDoCongTac lyDoCongTac) {
        log.debug("Request to save LyDoCongTac : {}", lyDoCongTac);
        return lyDoCongTacRepository.save(lyDoCongTac);
    }

    @Override
    public Optional<LyDoCongTac> partialUpdate(LyDoCongTac lyDoCongTac) {
        log.debug("Request to partially update LyDoCongTac : {}", lyDoCongTac);

        return lyDoCongTacRepository
            .findById(lyDoCongTac.getId())
            .map(existingLyDoCongTac -> {
                if (lyDoCongTac.getMaLyDo() != null) {
                    existingLyDoCongTac.setMaLyDo(lyDoCongTac.getMaLyDo());
                }
                if (lyDoCongTac.getTenLyDo() != null) {
                    existingLyDoCongTac.setTenLyDo(lyDoCongTac.getTenLyDo());
                }
                if (lyDoCongTac.getThuTuSX() != null) {
                    existingLyDoCongTac.setThuTuSX(lyDoCongTac.getThuTuSX());
                }

                return existingLyDoCongTac;
            })
            .map(lyDoCongTacRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LyDoCongTac> findAll(Pageable pageable) {
        log.debug("Request to get all LyDoCongTacs");
        return lyDoCongTacRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LyDoCongTac> findOne(Long id) {
        log.debug("Request to get LyDoCongTac : {}", id);
        return lyDoCongTacRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LyDoCongTac : {}", id);
        lyDoCongTacRepository.deleteById(id);
    }
}
