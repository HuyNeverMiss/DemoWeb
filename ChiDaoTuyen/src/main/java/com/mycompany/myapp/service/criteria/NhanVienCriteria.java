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

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.NhanVien} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.NhanVienResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nhan-viens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class NhanVienCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maNhanVien;

    private StringFilter chucVu;

    private LongFilter chiDaoTuyenId;

    private Boolean distinct;

    public NhanVienCriteria() {}

    public NhanVienCriteria(NhanVienCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maNhanVien = other.maNhanVien == null ? null : other.maNhanVien.copy();
        this.chucVu = other.chucVu == null ? null : other.chucVu.copy();
        this.chiDaoTuyenId = other.chiDaoTuyenId == null ? null : other.chiDaoTuyenId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public NhanVienCriteria copy() {
        return new NhanVienCriteria(this);
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

    public StringFilter getMaNhanVien() {
        return maNhanVien;
    }

    public StringFilter maNhanVien() {
        if (maNhanVien == null) {
            maNhanVien = new StringFilter();
        }
        return maNhanVien;
    }

    public void setMaNhanVien(StringFilter maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public StringFilter getChucVu() {
        return chucVu;
    }

    public StringFilter chucVu() {
        if (chucVu == null) {
            chucVu = new StringFilter();
        }
        return chucVu;
    }

    public void setChucVu(StringFilter chucVu) {
        this.chucVu = chucVu;
    }

    public LongFilter getChiDaoTuyenId() {
        return chiDaoTuyenId;
    }

    public LongFilter chiDaoTuyenId() {
        if (chiDaoTuyenId == null) {
            chiDaoTuyenId = new LongFilter();
        }
        return chiDaoTuyenId;
    }

    public void setChiDaoTuyenId(LongFilter chiDaoTuyenId) {
        this.chiDaoTuyenId = chiDaoTuyenId;
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
        final NhanVienCriteria that = (NhanVienCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maNhanVien, that.maNhanVien) &&
            Objects.equals(chucVu, that.chucVu) &&
            Objects.equals(chiDaoTuyenId, that.chiDaoTuyenId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maNhanVien, chucVu, chiDaoTuyenId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhanVienCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maNhanVien != null ? "maNhanVien=" + maNhanVien + ", " : "") +
            (chucVu != null ? "chucVu=" + chucVu + ", " : "") +
            (chiDaoTuyenId != null ? "chiDaoTuyenId=" + chiDaoTuyenId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
