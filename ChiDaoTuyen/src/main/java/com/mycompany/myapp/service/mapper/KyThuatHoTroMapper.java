package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link KyThuatHoTro} and its DTO {@link KyThuatHoTroDTO}.
 */
@Mapper(componentModel = "spring")
public interface KyThuatHoTroMapper extends EntityMapper<KyThuatHoTroDTO, KyThuatHoTro> {}
