package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChiDaoTuyen} and its DTO {@link ChiDaoTuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChiDaoTuyenMapper extends EntityMapper<ChiDaoTuyenDTO, ChiDaoTuyen> {}
