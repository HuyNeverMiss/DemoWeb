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
 * Criteria class for the {@link com.mycompany.myapp.domain.KetQuaCongTac} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.KetQuaCongTacResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ket-qua-cong-tacs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class KetQuaCongTacCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maKetQua;

    private StringFilter tenKetQua;

    private StringFilter thuTuSX;

    private LongFilter chiDaoTuyenId;

    private Boolean distinct;

    public KetQuaCongTacCriteria() {}

    public KetQuaCongTacCriteria(KetQuaCongTacCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maKetQua = other.maKetQua == null ? null : other.maKetQua.copy();
        this.tenKetQua = other.tenKetQua == null ? null : other.tenKetQua.copy();
        this.thuTuSX = other.thuTuSX == null ? null : other.thuTuSX.copy();
        this.chiDaoTuyenId = other.chiDaoTuyenId == null ? null : other.chiDaoTuyenId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public KetQuaCongTacCriteria copy() {
        return new KetQuaCongTacCriteria(this);
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

    public StringFilter getMaKetQua() {
        return maKetQua;
    }

    public StringFilter maKetQua() {
        if (maKetQua == null) {
            maKetQua = new StringFilter();
        }
        return maKetQua;
    }

    public void setMaKetQua(StringFilter maKetQua) {
        this.maKetQua = maKetQua;
    }

    public StringFilter getTenKetQua() {
        return tenKetQua;
    }

    public StringFilter tenKetQua() {
        if (tenKetQua == null) {
            tenKetQua = new StringFilter();
        }
        return tenKetQua;
    }

    public void setTenKetQua(StringFilter tenKetQua) {
        this.tenKetQua = tenKetQua;
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
        final KetQuaCongTacCriteria that = (KetQuaCongTacCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(maKetQua, that.maKetQua) &&
            Objects.equals(tenKetQua, that.tenKetQua) &&
            Objects.equals(thuTuSX, that.thuTuSX) &&
            Objects.equals(chiDaoTuyenId, that.chiDaoTuyenId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maKetQua, tenKetQua, thuTuSX, chiDaoTuyenId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KetQuaCongTacCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (maKetQua != null ? "maKetQua=" + maKetQua + ", " : "") +
            (tenKetQua != null ? "tenKetQua=" + tenKetQua + ", " : "") +
            (thuTuSX != null ? "thuTuSX=" + thuTuSX + ", " : "") +
            (chiDaoTuyenId != null ? "chiDaoTuyenId=" + chiDaoTuyenId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
