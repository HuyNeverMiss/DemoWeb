package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

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

    private InstantFilter ngayQuyetDinh;

    private StringFilter soHD;

    private InstantFilter ngayHD;

    private StringFilter noiDung;

    private InstantFilter ngayBatDau;

    private InstantFilter ngayKetThuc;

    private StringFilter ghiChu;

    private InstantFilter ngayTao;

    private StringFilter soBnKhamDieuTri;

    private StringFilter soBnPhauThuat;

    private StringFilter soCanBoChuyenGiao;

    private StringFilter luuTru;

    private StringFilter tienAn;

    private StringFilter tienO;

    private StringFilter tienDiLai;

    private StringFilter taiLieu;

    private StringFilter giangDay;

    private StringFilter khac;

    private LongFilter lyDoCongTacId;

    private LongFilter noiDenCongTacId;

    private LongFilter ketQuaCongTacId;

    private LongFilter kyThuatHoTroId;

    private LongFilter vatTuHoTroId;

    private LongFilter nhanVienId;

    private Boolean distinct;

    public ChiDaoTuyenCriteria() {}

    public ChiDaoTuyenCriteria(ChiDaoTuyenCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.soQuyetDinh = other.soQuyetDinh == null ? null : other.soQuyetDinh.copy();
        this.ngayQuyetDinh = other.ngayQuyetDinh == null ? null : other.ngayQuyetDinh.copy();
        this.soHD = other.soHD == null ? null : other.soHD.copy();
        this.ngayHD = other.ngayHD == null ? null : other.ngayHD.copy();
        this.noiDung = other.noiDung == null ? null : other.noiDung.copy();
        this.ngayBatDau = other.ngayBatDau == null ? null : other.ngayBatDau.copy();
        this.ngayKetThuc = other.ngayKetThuc == null ? null : other.ngayKetThuc.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.ngayTao = other.ngayTao == null ? null : other.ngayTao.copy();
        this.soBnKhamDieuTri = other.soBnKhamDieuTri == null ? null : other.soBnKhamDieuTri.copy();
        this.soBnPhauThuat = other.soBnPhauThuat == null ? null : other.soBnPhauThuat.copy();
        this.soCanBoChuyenGiao = other.soCanBoChuyenGiao == null ? null : other.soCanBoChuyenGiao.copy();
        this.luuTru = other.luuTru == null ? null : other.luuTru.copy();
        this.tienAn = other.tienAn == null ? null : other.tienAn.copy();
        this.tienO = other.tienO == null ? null : other.tienO.copy();
        this.tienDiLai = other.tienDiLai == null ? null : other.tienDiLai.copy();
        this.taiLieu = other.taiLieu == null ? null : other.taiLieu.copy();
        this.giangDay = other.giangDay == null ? null : other.giangDay.copy();
        this.khac = other.khac == null ? null : other.khac.copy();
        this.lyDoCongTacId = other.lyDoCongTacId == null ? null : other.lyDoCongTacId.copy();
        this.noiDenCongTacId = other.noiDenCongTacId == null ? null : other.noiDenCongTacId.copy();
        this.ketQuaCongTacId = other.ketQuaCongTacId == null ? null : other.ketQuaCongTacId.copy();
        this.kyThuatHoTroId = other.kyThuatHoTroId == null ? null : other.kyThuatHoTroId.copy();
        this.vatTuHoTroId = other.vatTuHoTroId == null ? null : other.vatTuHoTroId.copy();
        this.nhanVienId = other.nhanVienId == null ? null : other.nhanVienId.copy();
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

    public InstantFilter getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public InstantFilter ngayQuyetDinh() {
        if (ngayQuyetDinh == null) {
            ngayQuyetDinh = new InstantFilter();
        }
        return ngayQuyetDinh;
    }

    public void setNgayQuyetDinh(InstantFilter ngayQuyetDinh) {
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

    public InstantFilter getNgayHD() {
        return ngayHD;
    }

    public InstantFilter ngayHD() {
        if (ngayHD == null) {
            ngayHD = new InstantFilter();
        }
        return ngayHD;
    }

    public void setNgayHD(InstantFilter ngayHD) {
        this.ngayHD = ngayHD;
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

    public InstantFilter getNgayBatDau() {
        return ngayBatDau;
    }

    public InstantFilter ngayBatDau() {
        if (ngayBatDau == null) {
            ngayBatDau = new InstantFilter();
        }
        return ngayBatDau;
    }

    public void setNgayBatDau(InstantFilter ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public InstantFilter getNgayKetThuc() {
        return ngayKetThuc;
    }

    public InstantFilter ngayKetThuc() {
        if (ngayKetThuc == null) {
            ngayKetThuc = new InstantFilter();
        }
        return ngayKetThuc;
    }

    public void setNgayKetThuc(InstantFilter ngayKetThuc) {
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

    public InstantFilter getNgayTao() {
        return ngayTao;
    }

    public InstantFilter ngayTao() {
        if (ngayTao == null) {
            ngayTao = new InstantFilter();
        }
        return ngayTao;
    }

    public void setNgayTao(InstantFilter ngayTao) {
        this.ngayTao = ngayTao;
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

    public LongFilter getLyDoCongTacId() {
        return lyDoCongTacId;
    }

    public LongFilter lyDoCongTacId() {
        if (lyDoCongTacId == null) {
            lyDoCongTacId = new LongFilter();
        }
        return lyDoCongTacId;
    }

    public void setLyDoCongTacId(LongFilter lyDoCongTacId) {
        this.lyDoCongTacId = lyDoCongTacId;
    }

    public LongFilter getNoiDenCongTacId() {
        return noiDenCongTacId;
    }

    public LongFilter noiDenCongTacId() {
        if (noiDenCongTacId == null) {
            noiDenCongTacId = new LongFilter();
        }
        return noiDenCongTacId;
    }

    public void setNoiDenCongTacId(LongFilter noiDenCongTacId) {
        this.noiDenCongTacId = noiDenCongTacId;
    }

    public LongFilter getKetQuaCongTacId() {
        return ketQuaCongTacId;
    }

    public LongFilter ketQuaCongTacId() {
        if (ketQuaCongTacId == null) {
            ketQuaCongTacId = new LongFilter();
        }
        return ketQuaCongTacId;
    }

    public void setKetQuaCongTacId(LongFilter ketQuaCongTacId) {
        this.ketQuaCongTacId = ketQuaCongTacId;
    }

    public LongFilter getKyThuatHoTroId() {
        return kyThuatHoTroId;
    }

    public LongFilter kyThuatHoTroId() {
        if (kyThuatHoTroId == null) {
            kyThuatHoTroId = new LongFilter();
        }
        return kyThuatHoTroId;
    }

    public void setKyThuatHoTroId(LongFilter kyThuatHoTroId) {
        this.kyThuatHoTroId = kyThuatHoTroId;
    }

    public LongFilter getVatTuHoTroId() {
        return vatTuHoTroId;
    }

    public LongFilter vatTuHoTroId() {
        if (vatTuHoTroId == null) {
            vatTuHoTroId = new LongFilter();
        }
        return vatTuHoTroId;
    }

    public void setVatTuHoTroId(LongFilter vatTuHoTroId) {
        this.vatTuHoTroId = vatTuHoTroId;
    }

    public LongFilter getNhanVienId() {
        return nhanVienId;
    }

    public LongFilter nhanVienId() {
        if (nhanVienId == null) {
            nhanVienId = new LongFilter();
        }
        return nhanVienId;
    }

    public void setNhanVienId(LongFilter nhanVienId) {
        this.nhanVienId = nhanVienId;
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
            Objects.equals(noiDung, that.noiDung) &&
            Objects.equals(ngayBatDau, that.ngayBatDau) &&
            Objects.equals(ngayKetThuc, that.ngayKetThuc) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(ngayTao, that.ngayTao) &&
            Objects.equals(soBnKhamDieuTri, that.soBnKhamDieuTri) &&
            Objects.equals(soBnPhauThuat, that.soBnPhauThuat) &&
            Objects.equals(soCanBoChuyenGiao, that.soCanBoChuyenGiao) &&
            Objects.equals(luuTru, that.luuTru) &&
            Objects.equals(tienAn, that.tienAn) &&
            Objects.equals(tienO, that.tienO) &&
            Objects.equals(tienDiLai, that.tienDiLai) &&
            Objects.equals(taiLieu, that.taiLieu) &&
            Objects.equals(giangDay, that.giangDay) &&
            Objects.equals(khac, that.khac) &&
            Objects.equals(lyDoCongTacId, that.lyDoCongTacId) &&
            Objects.equals(noiDenCongTacId, that.noiDenCongTacId) &&
            Objects.equals(ketQuaCongTacId, that.ketQuaCongTacId) &&
            Objects.equals(kyThuatHoTroId, that.kyThuatHoTroId) &&
            Objects.equals(vatTuHoTroId, that.vatTuHoTroId) &&
            Objects.equals(nhanVienId, that.nhanVienId) &&
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
            noiDung,
            ngayBatDau,
            ngayKetThuc,
            ghiChu,
            ngayTao,
            soBnKhamDieuTri,
            soBnPhauThuat,
            soCanBoChuyenGiao,
            luuTru,
            tienAn,
            tienO,
            tienDiLai,
            taiLieu,
            giangDay,
            khac,
            lyDoCongTacId,
            noiDenCongTacId,
            ketQuaCongTacId,
            kyThuatHoTroId,
            vatTuHoTroId,
            nhanVienId,
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
            (noiDung != null ? "noiDung=" + noiDung + ", " : "") +
            (ngayBatDau != null ? "ngayBatDau=" + ngayBatDau + ", " : "") +
            (ngayKetThuc != null ? "ngayKetThuc=" + ngayKetThuc + ", " : "") +
            (ghiChu != null ? "ghiChu=" + ghiChu + ", " : "") +
            (ngayTao != null ? "ngayTao=" + ngayTao + ", " : "") +
            (soBnKhamDieuTri != null ? "soBnKhamDieuTri=" + soBnKhamDieuTri + ", " : "") +
            (soBnPhauThuat != null ? "soBnPhauThuat=" + soBnPhauThuat + ", " : "") +
            (soCanBoChuyenGiao != null ? "soCanBoChuyenGiao=" + soCanBoChuyenGiao + ", " : "") +
            (luuTru != null ? "luuTru=" + luuTru + ", " : "") +
            (tienAn != null ? "tienAn=" + tienAn + ", " : "") +
            (tienO != null ? "tienO=" + tienO + ", " : "") +
            (tienDiLai != null ? "tienDiLai=" + tienDiLai + ", " : "") +
            (taiLieu != null ? "taiLieu=" + taiLieu + ", " : "") +
            (giangDay != null ? "giangDay=" + giangDay + ", " : "") +
            (khac != null ? "khac=" + khac + ", " : "") +
            (lyDoCongTacId != null ? "lyDoCongTacId=" + lyDoCongTacId + ", " : "") +
            (noiDenCongTacId != null ? "noiDenCongTacId=" + noiDenCongTacId + ", " : "") +
            (ketQuaCongTacId != null ? "ketQuaCongTacId=" + ketQuaCongTacId + ", " : "") +
            (kyThuatHoTroId != null ? "kyThuatHoTroId=" + kyThuatHoTroId + ", " : "") +
            (vatTuHoTroId != null ? "vatTuHoTroId=" + vatTuHoTroId + ", " : "") +
            (nhanVienId != null ? "nhanVienId=" + nhanVienId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
