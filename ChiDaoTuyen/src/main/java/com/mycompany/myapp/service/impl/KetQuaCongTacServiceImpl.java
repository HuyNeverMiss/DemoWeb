package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.repository.KetQuaCongTacRepository;
import com.mycompany.myapp.service.KetQuaCongTacService;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import com.mycompany.myapp.service.mapper.KetQuaCongTacMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final KetQuaCongTacMapper ketQuaCongTacMapper;

    public KetQuaCongTacServiceImpl(KetQuaCongTacRepository ketQuaCongTacRepository, KetQuaCongTacMapper ketQuaCongTacMapper) {
        this.ketQuaCongTacRepository = ketQuaCongTacRepository;
        this.ketQuaCongTacMapper = ketQuaCongTacMapper;
    }

    @Override
    public KetQuaCongTacDTO save(KetQuaCongTacDTO ketQuaCongTacDTO) {
        log.debug("Request to save KetQuaCongTac : {}", ketQuaCongTacDTO);
        KetQuaCongTac ketQuaCongTac = ketQuaCongTacMapper.toEntity(ketQuaCongTacDTO);
        ketQuaCongTac = ketQuaCongTacRepository.save(ketQuaCongTac);
        return ketQuaCongTacMapper.toDto(ketQuaCongTac);
    }

    @Override
    public KetQuaCongTacDTO update(KetQuaCongTacDTO ketQuaCongTacDTO) {
        log.debug("Request to save KetQuaCongTac : {}", ketQuaCongTacDTO);
        KetQuaCongTac ketQuaCongTac = ketQuaCongTacMapper.toEntity(ketQuaCongTacDTO);
        ketQuaCongTac = ketQuaCongTacRepository.save(ketQuaCongTac);
        return ketQuaCongTacMapper.toDto(ketQuaCongTac);
    }

    @Override
    public Optional<KetQuaCongTacDTO> partialUpdate(KetQuaCongTacDTO ketQuaCongTacDTO) {
        log.debug("Request to partially update KetQuaCongTac : {}", ketQuaCongTacDTO);

        return ketQuaCongTacRepository
            .findById(ketQuaCongTacDTO.getId())
            .map(existingKetQuaCongTac -> {
                ketQuaCongTacMapper.partialUpdate(existingKetQuaCongTac, ketQuaCongTacDTO);

                return existingKetQuaCongTac;
            })
            .map(ketQuaCongTacRepository::save)
            .map(ketQuaCongTacMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KetQuaCongTacDTO> findAll() {
        log.debug("Request to get all KetQuaCongTacs");
        return ketQuaCongTacRepository.findAll().stream().map(ketQuaCongTacMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KetQuaCongTacDTO> findOne(Long id) {
        log.debug("Request to get KetQuaCongTac : {}", id);
        return ketQuaCongTacRepository.findById(id).map(ketQuaCongTacMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete KetQuaCongTac : {}", id);
        ketQuaCongTacRepository.deleteById(id);
    }
}
