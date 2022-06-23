package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotNull
    @Column(name = "so_quyet_dinh", nullable = false)
    private String soQuyetDinh;

    @NotNull
    @Column(name = "ngay_quyet_dinh", nullable = false)
    private Instant ngayQuyetDinh;

    @NotNull
    @Column(name = "so_hd", nullable = false)
    private String soHD;

    @NotNull
    @Column(name = "ngay_hd", nullable = false)
    private Instant ngayHD;

    @NotNull
    @Column(name = "noi_dung", nullable = false)
    private String noiDung;

    @NotNull
    @Column(name = "ngay_bat_dau", nullable = false)
    private Instant ngayBatDau;

    @NotNull
    @Column(name = "ngay_ket_thuc", nullable = false)
    private Instant ngayKetThuc;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @NotNull
    @Column(name = "ngay_tao", nullable = false)
    private Instant ngayTao;

    @Column(name = "so_bn_kham_dieu_tri")
    private String soBnKhamDieuTri;

    @Column(name = "so_bn_phau_thuat")
    private String soBnPhauThuat;

    @Column(name = "so_can_bo_chuyen_giao")
    private String soCanBoChuyenGiao;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private LyDoCongTac lyDoCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private NoiDenCongTac noiDenCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private KetQuaCongTac ketQuaCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private KyThuatHoTro kyThuatHoTro;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private VatTuHoTro vatTuHoTro;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private NhanVien nhanVien;

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

    public Instant getNgayQuyetDinh() {
        return this.ngayQuyetDinh;
    }

    public ChiDaoTuyen ngayQuyetDinh(Instant ngayQuyetDinh) {
        this.setNgayQuyetDinh(ngayQuyetDinh);
        return this;
    }

    public void setNgayQuyetDinh(Instant ngayQuyetDinh) {
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

    public Instant getNgayHD() {
        return this.ngayHD;
    }

    public ChiDaoTuyen ngayHD(Instant ngayHD) {
        this.setNgayHD(ngayHD);
        return this;
    }

    public void setNgayHD(Instant ngayHD) {
        this.ngayHD = ngayHD;
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

    public Instant getNgayBatDau() {
        return this.ngayBatDau;
    }

    public ChiDaoTuyen ngayBatDau(Instant ngayBatDau) {
        this.setNgayBatDau(ngayBatDau);
        return this;
    }

    public void setNgayBatDau(Instant ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Instant getNgayKetThuc() {
        return this.ngayKetThuc;
    }

    public ChiDaoTuyen ngayKetThuc(Instant ngayKetThuc) {
        this.setNgayKetThuc(ngayKetThuc);
        return this;
    }

    public void setNgayKetThuc(Instant ngayKetThuc) {
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

    public Instant getNgayTao() {
        return this.ngayTao;
    }

    public ChiDaoTuyen ngayTao(Instant ngayTao) {
        this.setNgayTao(ngayTao);
        return this;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
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

    public LyDoCongTac getLyDoCongTac() {
        return this.lyDoCongTac;
    }

    public void setLyDoCongTac(LyDoCongTac lyDoCongTac) {
        this.lyDoCongTac = lyDoCongTac;
    }

    public ChiDaoTuyen lyDoCongTac(LyDoCongTac lyDoCongTac) {
        this.setLyDoCongTac(lyDoCongTac);
        return this;
    }

    public NoiDenCongTac getNoiDenCongTac() {
        return this.noiDenCongTac;
    }

    public void setNoiDenCongTac(NoiDenCongTac noiDenCongTac) {
        this.noiDenCongTac = noiDenCongTac;
    }

    public ChiDaoTuyen noiDenCongTac(NoiDenCongTac noiDenCongTac) {
        this.setNoiDenCongTac(noiDenCongTac);
        return this;
    }

    public KetQuaCongTac getKetQuaCongTac() {
        return this.ketQuaCongTac;
    }

    public void setKetQuaCongTac(KetQuaCongTac ketQuaCongTac) {
        this.ketQuaCongTac = ketQuaCongTac;
    }

    public ChiDaoTuyen ketQuaCongTac(KetQuaCongTac ketQuaCongTac) {
        this.setKetQuaCongTac(ketQuaCongTac);
        return this;
    }

    public KyThuatHoTro getKyThuatHoTro() {
        return this.kyThuatHoTro;
    }

    public void setKyThuatHoTro(KyThuatHoTro kyThuatHoTro) {
        this.kyThuatHoTro = kyThuatHoTro;
    }

    public ChiDaoTuyen kyThuatHoTro(KyThuatHoTro kyThuatHoTro) {
        this.setKyThuatHoTro(kyThuatHoTro);
        return this;
    }

    public VatTuHoTro getVatTuHoTro() {
        return this.vatTuHoTro;
    }

    public void setVatTuHoTro(VatTuHoTro vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public ChiDaoTuyen vatTuHoTro(VatTuHoTro vatTuHoTro) {
        this.setVatTuHoTro(vatTuHoTro);
        return this;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ChiDaoTuyen nhanVien(NhanVien nhanVien) {
        this.setNhanVien(nhanVien);
        return this;
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
            "}";
    }
}
