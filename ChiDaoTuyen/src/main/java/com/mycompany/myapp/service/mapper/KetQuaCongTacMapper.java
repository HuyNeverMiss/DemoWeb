package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link KetQuaCongTac} and its DTO {@link KetQuaCongTacDTO}.
 */
@Mapper(componentModel = "spring")
public interface KetQuaCongTacMapper extends EntityMapper<KetQuaCongTacDTO, KetQuaCongTac> {
    @Mapping(target = "chiDaoTuyen", source = "chiDaoTuyen", qualifiedByName = "chiDaoTuyenId")
    KetQuaCongTacDTO toDto(KetQuaCongTac s);

    @Named("chiDaoTuyenId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ChiDaoTuyenDTO toDtoChiDaoTuyenId(ChiDaoTuyen chiDaoTuyen);
}
