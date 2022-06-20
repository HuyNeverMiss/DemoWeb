package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ChiDaoTuyen} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ChiDaoTuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /chi-dao-tuyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ChiDaoTuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter soQuyetDinh;

    private ZonedDateTimeFilter ngayQuyetDinh;

    private StringFilter soHD;

    private ZonedDateTimeFilter ngayHD;

    private StringFilter lyDoCT;

    private StringFilter noiDung;

    private StringFilter noiCongTac;

    private ZonedDateTimeFilter ngayBatDau;

    private ZonedDateTimeFilter ngayKetThuc;

    private StringFilter ghiChu;

    private ZonedDateTimeFilter ngayTao;

    private StringFilter nhanVien;

    private StringFilter kyThuatHoTro;

    private StringFilter vatTuHoTro;

    private StringFilter soBnKhamDieuTri;

    private StringFilter soBnPhauThuat;

    private StringFilter soCanBoChuyenGiao;

    private StringFilter ketQuaCongTac;

    private StringFilter luuTru;

    private StringFilter tienAn;

    private StringFilter tienO;

    private StringFilter tienDiLai;

    private StringFilter taiLieu;

    private StringFilter giangDay;

    private StringFilter khac;

    private Boolean distinct;

    public ChiDaoTuyenCriteria() {}

    public ChiDaoTuyenCriteria(ChiDaoTuyenCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.soQuyetDinh = other.soQuyetDinh == null ? null : other.soQuyetDinh.copy();
        this.ngayQuyetDinh = other.ngayQuyetDinh == null ? null : other.ngayQuyetDinh.copy();
        this.soHD = other.soHD == null ? null : other.soHD.copy();
        this.ngayHD = other.ngayHD == null ? null : other.ngayHD.copy();
        this.lyDoCT = other.lyDoCT == null ? null : other.lyDoCT.copy();
        this.noiDung = other.noiDung == null ? null : other.noiDung.copy();
        this.noiCongTac = other.noiCongTac == null ? null : other.noiCongTac.copy();
        this.ngayBatDau = other.ngayBatDau == null ? null : other.ngayBatDau.copy();
        this.ngayKetThuc = other.ngayKetThuc == null ? null : other.ngayKetThuc.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.ngayTao = other.ngayTao == null ? null : other.ngayTao.copy();
        this.nhanVien = other.nhanVien == null ? null : other.nhanVien.copy();
        this.kyThuatHoTro = other.kyThuatHoTro == null ? null : other.kyThuatHoTro.copy();
        this.vatTuHoTro = other.vatTuHoTro == null ? null : other.vatTuHoTro.copy();
        this.soBnKhamDieuTri = other.soBnKhamDieuTri == null ? null : other.soBnKhamDieuTri.copy();
        this.soBnPhauThuat = other.soBnPhauThuat == null ? null : other.soBnPhauThuat.copy();
        this.soCanBoChuyenGiao = other.soCanBoChuyenGiao == null ? null : other.soCanBoChuyenGiao.copy();
        this.ketQuaCongTac = other.ketQuaCongTac == null ? null : other.ketQuaCongTac.copy();
        this.luuTru = other.luuTru == null ? null : other.luuTru.copy();
        this.tienAn = other.tienAn == null ? null : other.tienAn.copy();
        this.tienO = other.tienO == null ? null : other.tienO.copy();
        this.tienDiLai = other.tienDiLai == null ? null : other.tienDiLai.copy();
        this.taiLieu = other.taiLieu == null ? null : other.taiLieu.copy();
        this.giangDay = other.giangDay == null ? null : other.giangDay.copy();
        this.khac = other.khac == null ? null : other.khac.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ChiDaoTuyenCriteria copy() {
        return new ChiDaoTuyenCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSoQuyetDinh() {
        return soQuyetDinh;
    }

    public StringFilter soQuyetDinh() {
        if (soQuyetDinh == null) {
            soQuyetDinh = new StringFilter();
        }
        return soQuyetDinh;
    }

    public void setSoQuyetDinh(StringFilter soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public ZonedDateTimeFilter getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public ZonedDateTimeFilter ngayQuyetDinh() {
        if (ngayQuyetDinh == null) {
            ngayQuyetDinh = new ZonedDateTimeFilter();
        }
        return ngayQuyetDinh;
    }

    public void setNgayQuyetDinh(ZonedDateTimeFilter ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public StringFilter getSoHD() {
        return soHD;
    }

    public StringFilter soHD() {
        if (soHD == null) {
            soHD = new StringFilter();
        }
        return soHD;
    }

    public void setSoHD(StringFilter soHD) {
        this.soHD = soHD;
    }

    public ZonedDateTimeFilter getNgayHD() {
        return ngayHD;
    }

    public ZonedDateTimeFilter ngayHD() {
        if (ngayHD == null) {
            ngayHD = new ZonedDateTimeFilter();
        }
        return ngayHD;
    }

    public void setNgayHD(ZonedDateTimeFilter ngayHD) {
        this.ngayHD = ngayHD;
    }

    public StringFilter getLyDoCT() {
        return lyDoCT;
    }

    public StringFilter lyDoCT() {
        if (lyDoCT == null) {
            lyDoCT = new StringFilter();
        }
        return lyDoCT;
    }

    public void setLyDoCT(StringFilter lyDoCT) {
        this.lyDoCT = lyDoCT;
    }

    public StringFilter getNoiDung() {
        return noiDung;
    }

    public StringFilter noiDung() {
        if (noiDung == null) {
            noiDung = new StringFilter();
        }
        return noiDung;
    }

    public void setNoiDung(StringFilter noiDung) {
        this.noiDung = noiDung;
    }

    public StringFilter getNoiCongTac() {
        return noiCongTac;
    }

    public StringFilter noiCongTac() {
        if (noiCongTac == null) {
            noiCongTac = new StringFilter();
        }
        return noiCongTac;
    }

    public void setNoiCongTac(StringFilter noiCongTac) {
        this.noiCongTac = noiCongTac;
    }

    public ZonedDateTimeFilter getNgayBatDau() {
        return ngayBatDau;
    }

    public ZonedDateTimeFilter ngayBatDau() {
        if (ngayBatDau == null) {
            ngayBatDau = new ZonedDateTimeFilter();
        }
        return ngayBatDau;
    }

    public void setNgayBatDau(ZonedDateTimeFilter ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public ZonedDateTimeFilter getNgayKetThuc() {
        return ngayKetThuc;
    }

    public ZonedDateTimeFilter ngayKetThuc() {
        if (ngayKetThuc == null) {
            ngayKetThuc = new ZonedDateTimeFilter();
        }
        return ngayKetThuc;
    }

    public void setNgayKetThuc(ZonedDateTimeFilter ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public StringFilter getGhiChu() {
        return ghiChu;
    }

    public StringFilter ghiChu() {
        if (ghiChu == null) {
            ghiChu = new StringFilter();
        }
        return ghiChu;
    }

    public void setGhiChu(StringFilter ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ZonedDateTimeFilter getNgayTao() {
        return ngayTao;
    }

    public ZonedDateTimeFilter ngayTao() {
        if (ngayTao == null) {
            ngayTao = new ZonedDateTimeFilter();
        }
        return ngayTao;
    }

    public void setNgayTao(ZonedDateTimeFilter ngayTao) {
        this.ngayTao = ngayTao;
    }

    public StringFilter getNhanVien() {
        return nhanVien;
    }

    public StringFilter nhanVien() {
        if (nhanVien == null) {
            nhanVien = new StringFilter();
        }
        return nhanVien;
    }

    public void setNhanVien(StringFilter nhanVien) {
        this.nhanVien = nhanVien;
    }

    public StringFilter getKyThuatHoTro() {
        return kyThuatHoTro;
    }

    public StringFilter kyThuatHoTro() {
        if (kyThuatHoTro == null) {
            kyThuatHoTro = new StringFilter();
        }
        return kyThuatHoTro;
    }

    public void setKyThuatHoTro(StringFilter kyThuatHoTro) {
        this.kyThuatHoTro = kyThuatHoTro;
    }

    public StringFilter getVatTuHoTro() {
        return vatTuHoTro;
    }

    public StringFilter vatTuHoTro() {
        if (vatTuHoTro == null) {
            vatTuHoTro = new StringFilter();
        }
        return vatTuHoTro;
    }

    public void setVatTuHoTro(StringFilter vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public StringFilter getSoBnKhamDieuTri() {
        return soBnKhamDieuTri;
    }

    public StringFilter soBnKhamDieuTri() {
        if (soBnKhamDieuTri == null) {
            soBnKhamDieuTri = new StringFilter();
        }
        return soBnKhamDieuTri;
    }

    public void setSoBnKhamDieuTri(StringFilter soBnKhamDieuTri) {
        this.soBnKhamDieuTri = soBnKhamDieuTri;
    }

    public StringFilter getSoBnPhauThuat() {
        return soBnPhauThuat;
    }

    public StringFilter soBnPhauThuat() {
        if (soBnPhauThuat == null) {
            soBnPhauThuat = new StringFilter();
        }
        return soBnPhauThuat;
    }

    public void setSoBnPhauThuat(StringFilter soBnPhauThuat) {
        this.soBnPhauThuat = soBnPhauThuat;
    }

    public StringFilter getSoCanBoChuyenGiao() {
        return soCanBoChuyenGiao;
    }

    public StringFilter soCanBoChuyenGiao() {
        if (soCanBoChuyenGiao == null) {
            soCanBoChuyenGiao = new StringFilter();
        }
        return soCanBoChuyenGiao;
    }

    public void setSoCanBoChuyenGiao(StringFilter soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public StringFilter getKetQuaCongTac() {
        return ketQuaCongTac;
    }

    public StringFilter ketQuaCongTac() {
        if (ketQuaCongTac == null) {
            ketQuaCongTac = new StringFilter();
        }
        return ketQuaCongTac;
    }

    public void setKetQuaCongTac(StringFilter ketQuaCongTac) {
        this.ketQuaCongTac = ketQuaCongTac;
    }

    public StringFilter getLuuTru() {
        return luuTru;
    }

    public StringFilter luuTru() {
        if (luuTru == null) {
            luuTru = new StringFilter();
        }
        return luuTru;
    }

    public void setLuuTru(StringFilter luuTru) {
        this.luuTru = luuTru;
    }

    public StringFilter getTienAn() {
        return tienAn;
    }

    public StringFilter tienAn() {
        if (tienAn == null) {
            tienAn = new StringFilter();
        }
        return tienAn;
    }

    public void setTienAn(StringFilter tienAn) {
        this.tienAn = tienAn;
    }

    public StringFilter getTienO() {
        return tienO;
    }

    public StringFilter tienO() {
        if (tienO == null) {
            tienO = new StringFilter();
        }
        return tienO;
    }

    public void setTienO(StringFilter tienO) {
        this.tienO = tienO;
    }

    public StringFilter getTienDiLai() {
        return tienDiLai;
    }

    public StringFilter tienDiLai() {
        if (tienDiLai == null) {
            tienDiLai = new StringFilter();
        }
        return tienDiLai;
    }

    public void setTienDiLai(StringFilter tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public StringFilter getTaiLieu() {
        return taiLieu;
    }

    public StringFilter taiLieu() {
        if (taiLieu == null) {
            taiLieu = new StringFilter();
        }
        return taiLieu;
    }

    public void setTaiLieu(StringFilter taiLieu) {
        this.taiLieu = taiLieu;
    }

    public StringFilter getGiangDay() {
        return giangDay;
    }

    public StringFilter giangDay() {
        if (giangDay == null) {
            giangDay = new StringFilter();
        }
        return giangDay;
    }

    public void setGiangDay(StringFilter giangDay) {
        this.giangDay = giangDay;
    }

    public StringFilter getKhac() {
        return khac;
    }

    public StringFilter khac() {
        if (khac == null) {
            khac = new StringFilter();
        }
        return khac;
    }

    public void setKhac(StringFilter khac) {
        this.khac = khac;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ChiDaoTuyenCriteria that = (ChiDaoTuyenCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(soQuyetDinh, that.soQuyetDinh) &&
            Objects.equals(ngayQuyetDinh, that.ngayQuyetDinh) &&
            Objects.equals(soHD, that.soHD) &&
            Objects.equals(ngayHD, that.ngayHD) &&
            Objects.equals(lyDoCT, that.lyDoCT) &&
            Objects.equals(noiDung, that.noiDung) &&
            Objects.equals(noiCongTac, that.noiCongTac) &&
            Objects.equals(ngayBatDau, that.ngayBatDau) &&
            Objects.equals(ngayKetThuc, that.ngayKetThuc) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(ngayTao, that.ngayTao) &&
            Objects.equals(nhanVien, that.nhanVien) &&
            Objects.equals(kyThuatHoTro, that.kyThuatHoTro) &&
            Objects.equals(vatTuHoTro, that.vatTuHoTro) &&
            Objects.equals(soBnKhamDieuTri, that.soBnKhamDieuTri) &&
            Objects.equals(soBnPhauThuat, that.soBnPhauThuat) &&
            Objects.equals(soCanBoChuyenGiao, that.soCanBoChuyenGiao) &&
            Objects.equals(ketQuaCongTac, that.ketQuaCongTac) &&
            Objects.equals(luuTru, that.luuTru) &&
            Objects.equals(tienAn, that.tienAn) &&
            Objects.equals(tienO, that.tienO) &&
            Objects.equals(tienDiLai, that.tienDiLai) &&
            Objects.equals(taiLieu, that.taiLieu) &&
            Objects.equals(giangDay, that.giangDay) &&
            Objects.equals(khac, that.khac) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            soQuyetDinh,
            ngayQuyetDinh,
            soHD,
            ngayHD,
            lyDoCT,
            noiDung,
            noiCongTac,
            ngayBatDau,
            ngayKetThuc,
            ghiChu,
            ngayTao,
            nhanVien,
            kyThuatHoTro,
            vatTuHoTro,
            soBnKhamDieuTri,
            soBnPhauThuat,
            soCanBoChuyenGiao,
            ketQuaCongTac,
            luuTru,
            tienAn,
            tienO,
            tienDiLai,
            taiLieu,
            giangDay,
            khac,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiDaoTuyenCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (soQuyetDinh != null ? "soQuyetDinh=" + soQuyetDinh + ", " : "") +
            (ngayQuyetDinh != null ? "ngayQuyetDinh=" + ngayQuyetDinh + ", " : "") +
            (soHD != null ? "soHD=" + soHD + ", " : "") +
            (ngayHD != null ? "ngayHD=" + ngayHD + ", " : "") +
            (lyDoCT != null ? "lyDoCT=" + lyDoCT + ", " : "") +
            (noiDung != null ? "noiDung=" + noiDung + ", " : "") +
            (noiCongTac != null ? "noiCongTac=" + noiCongTac + ", " : "") +
            (ngayBatDau != null ? "ngayBatDau=" + ngayBatDau + ", " : "") +
            (ngayKetThuc != null ? "ngayKetThuc=" + ngayKetThuc + ", " : "") +
            (ghiChu != null ? "ghiChu=" + ghiChu + ", " : "") +
            (ngayTao != null ? "ngayTao=" + ngayTao + ", " : "") +
            (nhanVien != null ? "nhanVien=" + nhanVien + ", " : "") +
            (kyThuatHoTro != null ? "kyThuatHoTro=" + kyThuatHoTro + ", " : "") +
            (vatTuHoTro != null ? "vatTuHoTro=" + vatTuHoTro + ", " : "") +
            (soBnKhamDieuTri != null ? "soBnKhamDieuTri=" + soBnKhamDieuTri + ", " : "") +
            (soBnPhauThuat != null ? "soBnPhauThuat=" + soBnPhauThuat + ", " : "") +
            (soCanBoChuyenGiao != null ? "soCanBoChuyenGiao=" + soCanBoChuyenGiao + ", " : "") +
            (ketQuaCongTac != null ? "ketQuaCongTac=" + ketQuaCongTac + ", " : "") +
            (luuTru != null ? "luuTru=" + luuTru + ", " : "") +
            (tienAn != null ? "tienAn=" + tienAn + ", " : "") +
            (tienO != null ? "tienO=" + tienO + ", " : "") +
            (tienDiLai != null ? "tienDiLai=" + tienDiLai + ", " : "") +
            (taiLieu != null ? "taiLieu=" + taiLieu + ", " : "") +
            (giangDay != null ? "giangDay=" + giangDay + ", " : "") +
            (khac != null ? "khac=" + khac + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
