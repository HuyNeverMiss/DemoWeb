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
 * Criteria class for the {@link com.mycompany.myapp.domain.LyDoCongTac} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.LyDoCongTacResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ly-do-cong-tacs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class LyDoCongTacCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maLyDo;

    private StringFilter tenLyDo;

    private StringFilter thuTuSX;

    private LongFilter chiDaoTuyenId;

    private Boolean distinct;

    public LyDoCongTacCriteria() {}

    public LyDoCongTacCriteria(LyDoCongTacCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maLyDo = other.maLyDo == null ? null : other.maLyDo.copy();
        this.tenLyDo = other.tenLyDo == null ? null : other.tenLyDo.copy();
        this.thuTuSX = other.thuTuSX == null ? null : other.thuTuSX.copy();
        this.chiDaoTuyenId = other.chiDaoTuyenId == null ? null : other.chiDaoTuyenId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public LyDoCongTacCriteria copy() {
        return new LyDoCongTacCriteria(this);
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

    public StringFilter getMaLyDo() {
        return maLyDo;
    }

    public StringFilter maLyDo() {
        if (maLyDo == null) {
            maLyDo = new StringFilter();
        }
        return maLyDo;
    }

    public void setMaLyDo(StringFilter maLyDo) {
        this.maLyDo = maLyDo;
    }

    public StringFilter getTenLyDo() {
        return tenLyDo;
    }

    public StringFilter tenLyDo() {
        if (tenLyDo == null) {
            tenLyDo = new StringFilter();
        }
        return tenLyDo;
    }

    public void setTenLyDo(StringFilter tenLyDo) {
        this.tenLyDo = tenLyDo;
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
        final LyDoCongTacCriteria that = (LyDoCongTacCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maLyDo, that.maLyDo) &&
            Objects.equals(tenLyDo, that.tenLyDo) &&
            Objects.equals(thuTuSX, that.thuTuSX) &&
            Objects.equals(chiDaoTuyenId, that.chiDaoTuyenId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maLyDo, tenLyDo, thuTuSX, chiDaoTuyenId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LyDoCongTacCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maLyDo != null ? "maLyDo=" + maLyDo + ", " : "") +
            (tenLyDo != null ? "tenLyDo=" + tenLyDo + ", " : "") +
            (thuTuSX != null ? "thuTuSX=" + thuTuSX + ", " : "") +
            (chiDaoTuyenId != null ? "chiDaoTuyenId=" + chiDaoTuyenId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
