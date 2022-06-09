package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link KyThuatHoTro} and its DTO {@link KyThuatHoTroDTO}.
 */
@Mapper(componentModel = "spring")
public interface KyThuatHoTroMapper extends EntityMapper<KyThuatHoTroDTO, KyThuatHoTro> {
    @Mapping(target = "chiDaoTuyen", source = "chiDaoTuyen", qualifiedByName = "chiDaoTuyenId")
    KyThuatHoTroDTO toDto(KyThuatHoTro s);

    @Named("chiDaoTuyenId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ChiDaoTuyenDTO toDtoChiDaoTuyenId(ChiDaoTuyen chiDaoTuyen);
}
