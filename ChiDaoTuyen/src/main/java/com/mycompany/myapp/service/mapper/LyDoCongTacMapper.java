package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LyDoCongTac} and its DTO {@link LyDoCongTacDTO}.
 */
@Mapper(componentModel = "spring")
public interface LyDoCongTacMapper extends EntityMapper<LyDoCongTacDTO, LyDoCongTac> {
    @Mapping(target = "chiDaoTuyen", source = "chiDaoTuyen", qualifiedByName = "chiDaoTuyenId")
    LyDoCongTacDTO toDto(LyDoCongTac s);

    @Named("chiDaoTuyenId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ChiDaoTuyenDTO toDtoChiDaoTuyenId(ChiDaoTuyen chiDaoTuyen);
}
