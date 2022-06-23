package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoiDenCongTac} and its DTO {@link NoiDenCongTacDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoiDenCongTacMapper extends EntityMapper<NoiDenCongTacDTO, NoiDenCongTac> {}
