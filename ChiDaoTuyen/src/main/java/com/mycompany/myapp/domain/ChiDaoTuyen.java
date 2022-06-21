package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ChiDaoTuyen.
 */
@Entity
@Table(name = "chi_dao_tuyen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChiDaoTuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "so_quyet_dinh")
    private String soQuyetDinh;

    @Column(name = "ngay_quyet_dinh")
    private ZonedDateTime ngayQuyetDinh;

    @Column(name = "so_hd")
    private String soHD;

    @Column(name = "ngay_hd")
    private ZonedDateTime ngayHD;

    @Column(name = "ly_do_cong_tac")
    private String lyDoCongTac;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "noi_den_cong_tac")
    private String noiDenCongTac;

    @Column(name = "ngay_bat_dau")
    private ZonedDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private ZonedDateTime ngayKetThuc;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_tao")
    private ZonedDateTime ngayTao;

    @Column(name = "nhan_vien")
    private String nhanVien;

    @Column(name = "ky_thuat_ho_tro")
    private String kyThuatHoTro;

    @Column(name = "vat_tu_ho_tro")
    private String vatTuHoTro;

    @Column(name = "so_bn_kham_dieu_tri")
    private String soBnKhamDieuTri;

    @Column(name = "so_bn_phau_thuat")
    private String soBnPhauThuat;

    @Column(name = "so_can_bo_chuyen_giao")
    private String soCanBoChuyenGiao;

    @Column(name = "ket_qua_cong_tac")
    private String ketQuaCongTac;

    @Column(name = "luu_tru")
    private String luuTru;

    @Column(name = "tien_an")
    private String tienAn;

    @Column(name = "tien_o")
    private String tienO;

    @Column(name = "tien_di_lai")
    private String tienDiLai;

    @Column(name = "tai_lieu")
    private String taiLieu;

    @Column(name = "giang_day")
    private String giangDay;

    @Column(name = "khac")
    private String khac;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChiDaoTuyen id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoQuyetDinh() {
        return this.soQuyetDinh;
    }

    public ChiDaoTuyen soQuyetDinh(String soQuyetDinh) {
        this.setSoQuyetDinh(soQuyetDinh);
        return this;
    }

    public void setSoQuyetDinh(String soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public ZonedDateTime getNgayQuyetDinh() {
        return this.ngayQuyetDinh;
    }

    public ChiDaoTuyen ngayQuyetDinh(ZonedDateTime ngayQuyetDinh) {
        this.setNgayQuyetDinh(ngayQuyetDinh);
        return this;
    }

    public void setNgayQuyetDinh(ZonedDateTime ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public String getSoHD() {
        return this.soHD;
    }

    public ChiDaoTuyen soHD(String soHD) {
        this.setSoHD(soHD);
        return this;
    }

    public void setSoHD(String soHD) {
        this.soHD = soHD;
    }

    public ZonedDateTime getNgayHD() {
        return this.ngayHD;
    }

    public ChiDaoTuyen ngayHD(ZonedDateTime ngayHD) {
        this.setNgayHD(ngayHD);
        return this;
    }

    public void setNgayHD(ZonedDateTime ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getLyDoCongTac() {
        return this.lyDoCongTac;
    }

    public ChiDaoTuyen lyDoCongTac(String lyDoCongTac) {
        this.setLyDoCongTac(lyDoCongTac);
        return this;
    }

    public void setLyDoCongTac(String lyDoCongTac) {
        this.lyDoCongTac = lyDoCongTac;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public ChiDaoTuyen noiDung(String noiDung) {
        this.setNoiDung(noiDung);
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDenCongTac() {
        return this.noiDenCongTac;
    }

    public ChiDaoTuyen noiDenCongTac(String noiDenCongTac) {
        this.setNoiDenCongTac(noiDenCongTac);
        return this;
    }

    public void setNoiDenCongTac(String noiDenCongTac) {
        this.noiDenCongTac = noiDenCongTac;
    }

    public ZonedDateTime getNgayBatDau() {
        return this.ngayBatDau;
    }

    public ChiDaoTuyen ngayBatDau(ZonedDateTime ngayBatDau) {
        this.setNgayBatDau(ngayBatDau);
        return this;
    }

    public void setNgayBatDau(ZonedDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public ZonedDateTime getNgayKetThuc() {
        return this.ngayKetThuc;
    }

    public ChiDaoTuyen ngayKetThuc(ZonedDateTime ngayKetThuc) {
        this.setNgayKetThuc(ngayKetThuc);
        return this;
    }

    public void setNgayKetThuc(ZonedDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public ChiDaoTuyen ghiChu(String ghiChu) {
        this.setGhiChu(ghiChu);
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ZonedDateTime getNgayTao() {
        return this.ngayTao;
    }

    public ChiDaoTuyen ngayTao(ZonedDateTime ngayTao) {
        this.setNgayTao(ngayTao);
        return this;
    }

    public void setNgayTao(ZonedDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNhanVien() {
        return this.nhanVien;
    }

    public ChiDaoTuyen nhanVien(String nhanVien) {
        this.setNhanVien(nhanVien);
        return this;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getKyThuatHoTro() {
        return this.kyThuatHoTro;
    }

    public ChiDaoTuyen kyThuatHoTro(String kyThuatHoTro) {
        this.setKyThuatHoTro(kyThuatHoTro);
        return this;
    }

    public void setKyThuatHoTro(String kyThuatHoTro) {
        this.kyThuatHoTro = kyThuatHoTro;
    }

    public String getVatTuHoTro() {
        return this.vatTuHoTro;
    }

    public ChiDaoTuyen vatTuHoTro(String vatTuHoTro) {
        this.setVatTuHoTro(vatTuHoTro);
        return this;
    }

    public void setVatTuHoTro(String vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public String getSoBnKhamDieuTri() {
        return this.soBnKhamDieuTri;
    }

    public ChiDaoTuyen soBnKhamDieuTri(String soBnKhamDieuTri) {
        this.setSoBnKhamDieuTri(soBnKhamDieuTri);
        return this;
    }

    public void setSoBnKhamDieuTri(String soBnKhamDieuTri) {
        this.soBnKhamDieuTri = soBnKhamDieuTri;
    }

    public String getSoBnPhauThuat() {
        return this.soBnPhauThuat;
    }

    public ChiDaoTuyen soBnPhauThuat(String soBnPhauThuat) {
        this.setSoBnPhauThuat(soBnPhauThuat);
        return this;
    }

    public void setSoBnPhauThuat(String soBnPhauThuat) {
        this.soBnPhauThuat = soBnPhauThuat;
    }

    public String getSoCanBoChuyenGiao() {
        return this.soCanBoChuyenGiao;
    }

    public ChiDaoTuyen soCanBoChuyenGiao(String soCanBoChuyenGiao) {
        this.setSoCanBoChuyenGiao(soCanBoChuyenGiao);
        return this;
    }

    public void setSoCanBoChuyenGiao(String soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public String getKetQuaCongTac() {
        return this.ketQuaCongTac;
    }

    public ChiDaoTuyen ketQuaCongTac(String ketQuaCongTac) {
        this.setKetQuaCongTac(ketQuaCongTac);
        return this;
    }

    public void setKetQuaCongTac(String ketQuaCongTac) {
        this.ketQuaCongTac = ketQuaCongTac;
    }

    public String getLuuTru() {
        return this.luuTru;
    }

    public ChiDaoTuyen luuTru(String luuTru) {
        this.setLuuTru(luuTru);
        return this;
    }

    public void setLuuTru(String luuTru) {
        this.luuTru = luuTru;
    }

    public String getTienAn() {
        return this.tienAn;
    }

    public ChiDaoTuyen tienAn(String tienAn) {
        this.setTienAn(tienAn);
        return this;
    }

    public void setTienAn(String tienAn) {
        this.tienAn = tienAn;
    }

    public String getTienO() {
        return this.tienO;
    }

    public ChiDaoTuyen tienO(String tienO) {
        this.setTienO(tienO);
        return this;
    }

    public void setTienO(String tienO) {
        this.tienO = tienO;
    }

    public String getTienDiLai() {
        return this.tienDiLai;
    }

    public ChiDaoTuyen tienDiLai(String tienDiLai) {
        this.setTienDiLai(tienDiLai);
        return this;
    }

    public void setTienDiLai(String tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public String getTaiLieu() {
        return this.taiLieu;
    }

    public ChiDaoTuyen taiLieu(String taiLieu) {
        this.setTaiLieu(taiLieu);
        return this;
    }

    public void setTaiLieu(String taiLieu) {
        this.taiLieu = taiLieu;
    }

    public String getGiangDay() {
        return this.giangDay;
    }

    public ChiDaoTuyen giangDay(String giangDay) {
        this.setGiangDay(giangDay);
        return this;
    }

    public void setGiangDay(String giangDay) {
        this.giangDay = giangDay;
    }

    public String getKhac() {
        return this.khac;
    }

    public ChiDaoTuyen khac(String khac) {
        this.setKhac(khac);
        return this;
    }

    public void setKhac(String khac) {
        this.khac = khac;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiDaoTuyen)) {
            return false;
        }
        return id != null && id.equals(((ChiDaoTuyen) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiDaoTuyen{" +
            "id=" + getId() +
            ", soQuyetDinh='" + getSoQuyetDinh() + "'" +
            ", ngayQuyetDinh='" + getNgayQuyetDinh() + "'" +
            ", soHD='" + getSoHD() + "'" +
            ", ngayHD='" + getNgayHD() + "'" +
            ", lyDoCongTac='" + getLyDoCongTac() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", noiDenCongTac='" + getNoiDenCongTac() + "'" +
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
