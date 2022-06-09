package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.VatTuHoTroDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VatTuHoTro} and its DTO {@link VatTuHoTroDTO}.
 */
@Mapper(componentModel = "spring")
public interface VatTuHoTroMapper extends EntityMapper<VatTuHoTroDTO, VatTuHoTro> {
    @Mapping(target = "chiDaoTuyen", source = "chiDaoTuyen", qualifiedByName = "chiDaoTuyenId")
    VatTuHoTroDTO toDto(VatTuHoTro s);

    @Named("chiDaoTuyenId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ChiDaoTuyenDTO toDtoChiDaoTuyenId(ChiDaoTuyen chiDaoTuyen);
}
