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
 * Criteria class for the {@link com.mycompany.myapp.domain.VatTuHoTro} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.VatTuHoTroResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /vat-tu-ho-tros?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class VatTuHoTroCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maVatTu;

    private StringFilter tenVatTu;

    private StringFilter thuTuSX;

    private LongFilter chiDaoTuyenId;

    private Boolean distinct;

    public VatTuHoTroCriteria() {}

    public VatTuHoTroCriteria(VatTuHoTroCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maVatTu = other.maVatTu == null ? null : other.maVatTu.copy();
        this.tenVatTu = other.tenVatTu == null ? null : other.tenVatTu.copy();
        this.thuTuSX = other.thuTuSX == null ? null : other.thuTuSX.copy();
        this.chiDaoTuyenId = other.chiDaoTuyenId == null ? null : other.chiDaoTuyenId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public VatTuHoTroCriteria copy() {
        return new VatTuHoTroCriteria(this);
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

    public StringFilter getMaVatTu() {
        return maVatTu;
    }

    public StringFilter maVatTu() {
        if (maVatTu == null) {
            maVatTu = new StringFilter();
        }
        return maVatTu;
    }

    public void setMaVatTu(StringFilter maVatTu) {
        this.maVatTu = maVatTu;
    }

    public StringFilter getTenVatTu() {
        return tenVatTu;
    }

    public StringFilter tenVatTu() {
        if (tenVatTu == null) {
            tenVatTu = new StringFilter();
        }
        return tenVatTu;
    }

    public void setTenVatTu(StringFilter tenVatTu) {
        this.tenVatTu = tenVatTu;
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
        final VatTuHoTroCriteria that = (VatTuHoTroCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maVatTu, that.maVatTu) &&
            Objects.equals(tenVatTu, that.tenVatTu) &&
            Objects.equals(thuTuSX, that.thuTuSX) &&
            Objects.equals(chiDaoTuyenId, that.chiDaoTuyenId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maVatTu, tenVatTu, thuTuSX, chiDaoTuyenId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VatTuHoTroCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maVatTu != null ? "maVatTu=" + maVatTu + ", " : "") +
            (tenVatTu != null ? "tenVatTu=" + tenVatTu + ", " : "") +
            (thuTuSX != null ? "thuTuSX=" + thuTuSX + ", " : "") +
            (chiDaoTuyenId != null ? "chiDaoTuyenId=" + chiDaoTuyenId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
