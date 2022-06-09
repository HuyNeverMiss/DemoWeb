package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ChiDaoTuyen} entity.
 */
public class ChiDaoTuyenDTO implements Serializable {

    private Long id;

    private String soQuyetDinh;

    private ZonedDateTime ngayQuyetDinh;

    private String soHD;

    private ZonedDateTime ngayHD;

    private String lyDoCT;

    private String noiDung;

    private String noiCongTac;

    private ZonedDateTime ngayBatDau;

    private ZonedDateTime ngayKetThuc;

    private String ghiChu;

    private ZonedDateTime ngayTao;

    private String nhanVien;

    private String kyThuatHoTro;

    private String vatTuHoTro;

    private String soBnKhamDieuTri;

    private String soBnPhauThuat;

    private String soCanBoChuyenGiao;

    private String ketQuaCongTac;

    private String luuTru;

    private String tienAn;

    private String tienO;

    private String tienDiLai;

    private String taiLieu;

    private String giangDay;

    private String khac;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoQuyetDinh() {
        return soQuyetDinh;
    }

    public void setSoQuyetDinh(String soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public ZonedDateTime getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public void setNgayQuyetDinh(ZonedDateTime ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public String getSoHD() {
        return soHD;
    }

    public void setSoHD(String soHD) {
        this.soHD = soHD;
    }

    public ZonedDateTime getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(ZonedDateTime ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getLyDoCT() {
        return lyDoCT;
    }

    public void setLyDoCT(String lyDoCT) {
        this.lyDoCT = lyDoCT;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiCongTac() {
        return noiCongTac;
    }

    public void setNoiCongTac(String noiCongTac) {
        this.noiCongTac = noiCongTac;
    }

    public ZonedDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(ZonedDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public ZonedDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(ZonedDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ZonedDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(ZonedDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getKyThuatHoTro() {
        return kyThuatHoTro;
    }

    public void setKyThuatHoTro(String kyThuatHoTro) {
        this.kyThuatHoTro = kyThuatHoTro;
    }

    public String getVatTuHoTro() {
        return vatTuHoTro;
    }

    public void setVatTuHoTro(String vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public String getSoBnKhamDieuTri() {
        return soBnKhamDieuTri;
    }

    public void setSoBnKhamDieuTri(String soBnKhamDieuTri) {
        this.soBnKhamDieuTri = soBnKhamDieuTri;
    }

    public String getSoBnPhauThuat() {
        return soBnPhauThuat;
    }

    public void setSoBnPhauThuat(String soBnPhauThuat) {
        this.soBnPhauThuat = soBnPhauThuat;
    }

    public String getSoCanBoChuyenGiao() {
        return soCanBoChuyenGiao;
    }

    public void setSoCanBoChuyenGiao(String soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public String getKetQuaCongTac() {
        return ketQuaCongTac;
    }

    public void setKetQuaCongTac(String ketQuaCongTac) {
        this.ketQuaCongTac = ketQuaCongTac;
    }

    public String getLuuTru() {
        return luuTru;
    }

    public void setLuuTru(String luuTru) {
        this.luuTru = luuTru;
    }

    public String getTienAn() {
        return tienAn;
    }

    public void setTienAn(String tienAn) {
        this.tienAn = tienAn;
    }

    public String getTienO() {
        return tienO;
    }

    public void setTienO(String tienO) {
        this.tienO = tienO;
    }

    public String getTienDiLai() {
        return tienDiLai;
    }

    public void setTienDiLai(String tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public String getTaiLieu() {
        return taiLieu;
    }

    public void setTaiLieu(String taiLieu) {
        this.taiLieu = taiLieu;
    }

    public String getGiangDay() {
        return giangDay;
    }

    public void setGiangDay(String giangDay) {
        this.giangDay = giangDay;
    }

    public String getKhac() {
        return khac;
    }

    public void setKhac(String khac) {
        this.khac = khac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiDaoTuyenDTO)) {
            return false;
        }

        ChiDaoTuyenDTO chiDaoTuyenDTO = (ChiDaoTuyenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chiDaoTuyenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiDaoTuyenDTO{" +
            "id=" + getId() +
            ", soQuyetDinh='" + getSoQuyetDinh() + "'" +
            ", ngayQuyetDinh='" + getNgayQuyetDinh() + "'" +
            ", soHD='" + getSoHD() + "'" +
            ", ngayHD='" + getNgayHD() + "'" +
            ", lyDoCT='" + getLyDoCT() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", noiCongTac='" + getNoiCongTac() + "'" +
            ", ngayBatDau='" + getNgayBatDau() + "'" +
            ", ngayKetThuc='" + getNgayKetThuc() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", nhanVien='" + getNhanVien() + "'" +
            ", kyThuatHoTro='" + getKyThuatHoTro() + "'" +
            ", vatTuHoTro='" + getVatTuHoTro() + "'" +
            ", soBnKhamDieuTri='" + getSoBnKhamDieuTri() + "'" +
            ", soBnPhauThuat='" + getSoBnPhauThuat() + "'" +
            ", soCanBoChuyenGiao='" + getSoCanBoChuyenGiao() + "'" +
            ", ketQuaCongTac='" + getKetQuaCongTac() + "'" +
            ", luuTru='" + getLuuTru() + "'" +
            ", tienAn='" + getTienAn() + "'" +
            ", tienO='" + getTienO() + "'" +
            ", tienDiLai='" + getTienDiLai() + "'" +
            ", taiLieu='" + getTaiLieu() + "'" +
            ", giangDay='" + getGiangDay() + "'" +
            ", khac='" + getKhac() + "'" +
            "}";
    }
}
