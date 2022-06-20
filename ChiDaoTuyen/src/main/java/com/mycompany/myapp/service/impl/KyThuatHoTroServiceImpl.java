package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.repository.KyThuatHoTroRepository;
import com.mycompany.myapp.service.KyThuatHoTroService;
import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
import com.mycompany.myapp.service.mapper.KyThuatHoTroMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final KyThuatHoTroMapper kyThuatHoTroMapper;

    public KyThuatHoTroServiceImpl(KyThuatHoTroRepository kyThuatHoTroRepository, KyThuatHoTroMapper kyThuatHoTroMapper) {
        this.kyThuatHoTroRepository = kyThuatHoTroRepository;
        this.kyThuatHoTroMapper = kyThuatHoTroMapper;
    }

    @Override
    public KyThuatHoTroDTO save(KyThuatHoTroDTO kyThuatHoTroDTO) {
        log.debug("Request to save KyThuatHoTro : {}", kyThuatHoTroDTO);
        KyThuatHoTro kyThuatHoTro = kyThuatHoTroMapper.toEntity(kyThuatHoTroDTO);
        kyThuatHoTro = kyThuatHoTroRepository.save(kyThuatHoTro);
        return kyThuatHoTroMapper.toDto(kyThuatHoTro);
    }

    @Override
    public KyThuatHoTroDTO update(KyThuatHoTroDTO kyThuatHoTroDTO) {
        log.debug("Request to save KyThuatHoTro : {}", kyThuatHoTroDTO);
        KyThuatHoTro kyThuatHoTro = kyThuatHoTroMapper.toEntity(kyThuatHoTroDTO);
        kyThuatHoTro = kyThuatHoTroRepository.save(kyThuatHoTro);
        return kyThuatHoTroMapper.toDto(kyThuatHoTro);
    }

    @Override
    public Optional<KyThuatHoTroDTO> partialUpdate(KyThuatHoTroDTO kyThuatHoTroDTO) {
        log.debug("Request to partially update KyThuatHoTro : {}", kyThuatHoTroDTO);

        return kyThuatHoTroRepository
            .findById(kyThuatHoTroDTO.getId())
            .map(existingKyThuatHoTro -> {
                kyThuatHoTroMapper.partialUpdate(existingKyThuatHoTro, kyThuatHoTroDTO);

                return existingKyThuatHoTro;
            })
            .map(kyThuatHoTroRepository::save)
            .map(kyThuatHoTroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KyThuatHoTroDTO> findAll() {
        log.debug("Request to get all KyThuatHoTros");
        return kyThuatHoTroRepository.findAll().stream().map(kyThuatHoTroMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KyThuatHoTroDTO> findOne(Long id) {
        log.debug("Request to get KyThuatHoTro : {}", id);
        return kyThuatHoTroRepository.findById(id).map(kyThuatHoTroMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete KyThuatHoTro : {}", id);
        kyThuatHoTroRepository.deleteById(id);
    }
}
