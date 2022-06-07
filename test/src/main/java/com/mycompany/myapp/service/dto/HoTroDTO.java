package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.HoTro} entity.
 */
public class HoTroDTO implements Serializable {

    private Long id;

    private Integer maNoiDung;

    private ZonedDateTime ngayTao;

    private String nhanVienCV;

    private String kTHoTro;

    private String vatTuHoTro;

    private Integer soBnKhamDieuTri;

    private Integer soBnPhauThuat;

    private Integer soCanBoChuyenGiao;

    private String danhGiaKQCongTac;

    private ChiDaoTuyenDTO chiDaoTuyen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaNoiDung() {
        return maNoiDung;
    }

    public void setMaNoiDung(Integer maNoiDung) {
        this.maNoiDung = maNoiDung;
    }

    public ZonedDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(ZonedDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNhanVienCV() {
        return nhanVienCV;
    }

    public void setNhanVienCV(String nhanVienCV) {
        this.nhanVienCV = nhanVienCV;
    }

    public String getkTHoTro() {
        return kTHoTro;
    }

    public void setkTHoTro(String kTHoTro) {
        this.kTHoTro = kTHoTro;
    }

    public String getVatTuHoTro() {
        return vatTuHoTro;
    }

    public void setVatTuHoTro(String vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public Integer getSoBnKhamDieuTri() {
        return soBnKhamDieuTri;
    }

    public void setSoBnKhamDieuTri(Integer soBnKhamDieuTri) {
        this.soBnKhamDieuTri = soBnKhamDieuTri;
    }

    public Integer getSoBnPhauThuat() {
        return soBnPhauThuat;
    }

    public void setSoBnPhauThuat(Integer soBnPhauThuat) {
        this.soBnPhauThuat = soBnPhauThuat;
    }

    public Integer getSoCanBoChuyenGiao() {
        return soCanBoChuyenGiao;
    }

    public void setSoCanBoChuyenGiao(Integer soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public String getDanhGiaKQCongTac() {
        return danhGiaKQCongTac;
    }

    public void setDanhGiaKQCongTac(String danhGiaKQCongTac) {
        this.danhGiaKQCongTac = danhGiaKQCongTac;
    }

    public ChiDaoTuyenDTO getChiDaoTuyen() {
        return chiDaoTuyen;
    }

    public void setChiDaoTuyen(ChiDaoTuyenDTO chiDaoTuyen) {
        this.chiDaoTuyen = chiDaoTuyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HoTroDTO)) {
            return false;
        }

        HoTroDTO hoTroDTO = (HoTroDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hoTroDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HoTroDTO{" +
            "id=" + getId() +
            ", maNoiDung=" + getMaNoiDung() +
            ", ngayTao='" + getNgayTao() + "'" +
            ", nhanVienCV='" + getNhanVienCV() + "'" +
            ", kTHoTro='" + getkTHoTro() + "'" +
            ", vatTuHoTro='" + getVatTuHoTro() + "'" +
            ", soBnKhamDieuTri=" + getSoBnKhamDieuTri() +
            ", soBnPhauThuat=" + getSoBnPhauThuat() +
            ", soCanBoChuyenGiao=" + getSoCanBoChuyenGiao() +
            ", danhGiaKQCongTac='" + getDanhGiaKQCongTac() + "'" +
            ", chiDaoTuyen=" + getChiDaoTuyen() +
            "}";
    }
}
