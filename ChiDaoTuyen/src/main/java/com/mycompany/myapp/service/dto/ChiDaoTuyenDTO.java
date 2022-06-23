package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ChiDaoTuyen} entity.
 */
public class ChiDaoTuyenDTO implements Serializable {

    private Long id;

    @NotNull
    private String soQuyetDinh;

    @NotNull
    private Instant ngayQuyetDinh;

    @NotNull
    private String soHD;

    @NotNull
    private Instant ngayHD;

    @NotNull
    private String noiDung;

    @NotNull
    private Instant ngayBatDau;

    @NotNull
    private Instant ngayKetThuc;

    private String ghiChu;

    @NotNull
    private Instant ngayTao;

    private String soBnKhamDieuTri;

    private String soBnPhauThuat;

    private String soCanBoChuyenGiao;

    private String luuTru;

    private String tienAn;

    private String tienO;

    private String tienDiLai;

    private String taiLieu;

    private String giangDay;

    private String khac;

    private LyDoCongTacDTO lyDoCongTac;

    private NoiDenCongTacDTO noiDenCongTac;

    private KetQuaCongTacDTO ketQuaCongTac;

    private KyThuatHoTroDTO kyThuatHoTro;

    private VatTuHoTroDTO vatTuHoTro;

    private NhanVienDTO nhanVien;

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

    public Instant getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public void setNgayQuyetDinh(Instant ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public String getSoHD() {
        return soHD;
    }

    public void setSoHD(String soHD) {
        this.soHD = soHD;
    }

    public Instant getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(Instant ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Instant getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Instant ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Instant getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Instant ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
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

    public LyDoCongTacDTO getLyDoCongTac() {
        return lyDoCongTac;
    }

    public void setLyDoCongTac(LyDoCongTacDTO lyDoCongTac) {
        this.lyDoCongTac = lyDoCongTac;
    }

    public NoiDenCongTacDTO getNoiDenCongTac() {
        return noiDenCongTac;
    }

    public void setNoiDenCongTac(NoiDenCongTacDTO noiDenCongTac) {
        this.noiDenCongTac = noiDenCongTac;
    }

    public KetQuaCongTacDTO getKetQuaCongTac() {
        return ketQuaCongTac;
    }

    public void setKetQuaCongTac(KetQuaCongTacDTO ketQuaCongTac) {
        this.ketQuaCongTac = ketQuaCongTac;
    }

    public KyThuatHoTroDTO getKyThuatHoTro() {
        return kyThuatHoTro;
    }

    public void setKyThuatHoTro(KyThuatHoTroDTO kyThuatHoTro) {
        this.kyThuatHoTro = kyThuatHoTro;
    }

    public VatTuHoTroDTO getVatTuHoTro() {
        return vatTuHoTro;
    }

    public void setVatTuHoTro(VatTuHoTroDTO vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public NhanVienDTO getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVienDTO nhanVien) {
        this.nhanVien = nhanVien;
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
            ", noiDung='" + getNoiDung() + "'" +
            ", ngayBatDau='" + getNgayBatDau() + "'" +
            ", ngayKetThuc='" + getNgayKetThuc() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", soBnKhamDieuTri='" + getSoBnKhamDieuTri() + "'" +
            ", soBnPhauThuat='" + getSoBnPhauThuat() + "'" +
            ", soCanBoChuyenGiao='" + getSoCanBoChuyenGiao() + "'" +
            ", luuTru='" + getLuuTru() + "'" +
            ", tienAn='" + getTienAn() + "'" +
            ", tienO='" + getTienO() + "'" +
            ", tienDiLai='" + getTienDiLai() + "'" +
            ", taiLieu='" + getTaiLieu() + "'" +
            ", giangDay='" + getGiangDay() + "'" +
            ", khac='" + getKhac() + "'" +
            ", lyDoCongTac=" + getLyDoCongTac() +
            ", noiDenCongTac=" + getNoiDenCongTac() +
            ", ketQuaCongTac=" + getKetQuaCongTac() +
            ", kyThuatHoTro=" + getKyThuatHoTro() +
            ", vatTuHoTro=" + getVatTuHoTro() +
            ", nhanVien=" + getNhanVien() +
            "}";
    }
}
