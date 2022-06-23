package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.domain.NhanVien;
import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import com.mycompany.myapp.service.dto.NhanVienDTO;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import com.mycompany.myapp.service.dto.VatTuHoTroDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChiDaoTuyen} and its DTO {@link ChiDaoTuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChiDaoTuyenMapper extends EntityMapper<ChiDaoTuyenDTO, ChiDaoTuyen> {
    @Mapping(target = "lyDoCongTac", source = "lyDoCongTac", qualifiedByName = "lyDoCongTacId")
    @Mapping(target = "noiDenCongTac", source = "noiDenCongTac", qualifiedByName = "noiDenCongTacId")
    @Mapping(target = "ketQuaCongTac", source = "ketQuaCongTac", qualifiedByName = "ketQuaCongTacId")
    @Mapping(target = "kyThuatHoTro", source = "kyThuatHoTro", qualifiedByName = "kyThuatHoTroId")
    @Mapping(target = "vatTuHoTro", source = "vatTuHoTro", qualifiedByName = "vatTuHoTroId")
    @Mapping(target = "nhanVien", source = "nhanVien", qualifiedByName = "nhanVienId")
    ChiDaoTuyenDTO toDto(ChiDaoTuyen s);

    @Named("lyDoCongTacId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LyDoCongTacDTO toDtoLyDoCongTacId(LyDoCongTac lyDoCongTac);

    @Named("noiDenCongTacId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NoiDenCongTacDTO toDtoNoiDenCongTacId(NoiDenCongTac noiDenCongTac);

    @Named("ketQuaCongTacId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    KetQuaCongTacDTO toDtoKetQuaCongTacId(KetQuaCongTac ketQuaCongTac);

    @Named("kyThuatHoTroId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    KyThuatHoTroDTO toDtoKyThuatHoTroId(KyThuatHoTro kyThuatHoTro);

    @Named("vatTuHoTroId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VatTuHoTroDTO toDtoVatTuHoTroId(VatTuHoTro vatTuHoTro);

    @Named("nhanVienId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NhanVienDTO toDtoNhanVienId(NhanVien nhanVien);
}
