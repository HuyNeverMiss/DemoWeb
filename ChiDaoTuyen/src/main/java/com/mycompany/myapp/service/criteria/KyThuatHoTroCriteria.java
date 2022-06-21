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
 * Criteria class for the {@link com.mycompany.myapp.domain.KyThuatHoTro} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.KyThuatHoTroResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ky-thuat-ho-tros?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class KyThuatHoTroCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maKyThuat;

    private StringFilter tenKyThuat;

    private StringFilter thuTuSX;

    private LongFilter chiDaoTuyenId;

    private Boolean distinct;

    public KyThuatHoTroCriteria() {}

    public KyThuatHoTroCriteria(KyThuatHoTroCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maKyThuat = other.maKyThuat == null ? null : other.maKyThuat.copy();
        this.tenKyThuat = other.tenKyThuat == null ? null : other.tenKyThuat.copy();
        this.thuTuSX = other.thuTuSX == null ? null : other.thuTuSX.copy();
        this.chiDaoTuyenId = other.chiDaoTuyenId == null ? null : other.chiDaoTuyenId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public KyThuatHoTroCriteria copy() {
        return new KyThuatHoTroCriteria(this);
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

    public StringFilter getMaKyThuat() {
        return maKyThuat;
    }

    public StringFilter maKyThuat() {
        if (maKyThuat == null) {
            maKyThuat = new StringFilter();
        }
        return maKyThuat;
    }

    public void setMaKyThuat(StringFilter maKyThuat) {
        this.maKyThuat = maKyThuat;
    }

    public StringFilter getTenKyThuat() {
        return tenKyThuat;
    }

    public StringFilter tenKyThuat() {
        if (tenKyThuat == null) {
            tenKyThuat = new StringFilter();
        }
        return tenKyThuat;
    }

    public void setTenKyThuat(StringFilter tenKyThuat) {
        this.tenKyThuat = tenKyThuat;
    }

    public StringFilter getThuTuSX() {
        return thuTuSX;
    }

    public StringFilter thuTuSX() {
        if (thuTuSX == null) {
            thuTuSX = new StringFilter();
        }
        return thuTuSX;
    }

    public void setThuTuSX(StringFilter thuTuSX) {
        this.thuTuSX = thuTuSX;
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
        final KyThuatHoTroCriteria that = (KyThuatHoTroCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maKyThuat, that.maKyThuat) &&
            Objects.equals(tenKyThuat, that.tenKyThuat) &&
            Objects.equals(thuTuSX, that.thuTuSX) &&
            Objects.equals(chiDaoTuyenId, that.chiDaoTuyenId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maKyThuat, tenKyThuat, thuTuSX, chiDaoTuyenId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KyThuatHoTroCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maKyThuat != null ? "maKyThuat=" + maKyThuat + ", " : "") +
            (tenKyThuat != null ? "tenKyThuat=" + tenKyThuat + ", " : "") +
            (thuTuSX != null ? "thuTuSX=" + thuTuSX + ", " : "") +
            (chiDaoTuyenId != null ? "chiDaoTuyenId=" + chiDaoTuyenId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
