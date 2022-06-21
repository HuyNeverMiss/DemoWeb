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
 * Criteria class for the {@link com.mycompany.myapp.domain.NoiDenCongTac} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.NoiDenCongTacResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /noi-den-cong-tacs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class NoiDenCongTacCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maNoiDen;

    private StringFilter tenNoiDen;

    private StringFilter thuTuSX;

    private LongFilter chiDaoTuyenId;

    private Boolean distinct;

    public NoiDenCongTacCriteria() {}

    public NoiDenCongTacCriteria(NoiDenCongTacCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maNoiDen = other.maNoiDen == null ? null : other.maNoiDen.copy();
        this.tenNoiDen = other.tenNoiDen == null ? null : other.tenNoiDen.copy();
        this.thuTuSX = other.thuTuSX == null ? null : other.thuTuSX.copy();
        this.chiDaoTuyenId = other.chiDaoTuyenId == null ? null : other.chiDaoTuyenId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public NoiDenCongTacCriteria copy() {
        return new NoiDenCongTacCriteria(this);
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

    public StringFilter getMaNoiDen() {
        return maNoiDen;
    }

    public StringFilter maNoiDen() {
        if (maNoiDen == null) {
            maNoiDen = new StringFilter();
        }
        return maNoiDen;
    }

    public void setMaNoiDen(StringFilter maNoiDen) {
        this.maNoiDen = maNoiDen;
    }

    public StringFilter getTenNoiDen() {
        return tenNoiDen;
    }

    public StringFilter tenNoiDen() {
        if (tenNoiDen == null) {
            tenNoiDen = new StringFilter();
        }
        return tenNoiDen;
    }

    public void setTenNoiDen(StringFilter tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
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
        final NoiDenCongTacCriteria that = (NoiDenCongTacCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maNoiDen, that.maNoiDen) &&
            Objects.equals(tenNoiDen, that.tenNoiDen) &&
            Objects.equals(thuTuSX, that.thuTuSX) &&
            Objects.equals(chiDaoTuyenId, that.chiDaoTuyenId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maNoiDen, tenNoiDen, thuTuSX, chiDaoTuyenId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoiDenCongTacCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maNoiDen != null ? "maNoiDen=" + maNoiDen + ", " : "") +
            (tenNoiDen != null ? "tenNoiDen=" + tenNoiDen + ", " : "") +
            (thuTuSX != null ? "thuTuSX=" + thuTuSX + ", " : "") +
            (chiDaoTuyenId != null ? "chiDaoTuyenId=" + chiDaoTuyenId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
