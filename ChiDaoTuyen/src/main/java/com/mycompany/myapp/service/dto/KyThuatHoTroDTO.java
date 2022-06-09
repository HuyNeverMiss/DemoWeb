package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.KyThuatHoTro} entity.
 */
public class KyThuatHoTroDTO implements Serializable {

    private Long id;

    private String maKyThuat;

    private String tenKyThuat;

    private String thuTuSX;

    private ChiDaoTuyenDTO chiDaoTuyen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKyThuat() {
        return maKyThuat;
    }

    public void setMaKyThuat(String maKyThuat) {
        this.maKyThuat = maKyThuat;
    }

    public String getTenKyThuat() {
        return tenKyThuat;
    }

    public void setTenKyThuat(String tenKyThuat) {
        this.tenKyThuat = tenKyThuat;
    }

    public String getThuTuSX() {
        return thuTuSX;
    }

    public void setThuTuSX(String thuTuSX) {
        this.thuTuSX = thuTuSX;
    }

    public ChiDaoTuyenDTO getChiDaoTuyen() {
        return chiDaoTuyen;
    }

    public void setChiDaoTuyen(ChiDaoTuyenDTO chiDaoTuyen) {
        this.chiDaoTuyen = chiDaoTuyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KyThuatHoTroDTO)) {
            return false;
        }

        KyThuatHoTroDTO kyThuatHoTroDTO = (KyThuatHoTroDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, kyThuatHoTroDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KyThuatHoTroDTO{" +
            "id=" + getId() +
            ", maKyThuat='" + getMaKyThuat() + "'" +
            ", tenKyThuat='" + getTenKyThuat() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            ", chiDaoTuyen=" + getChiDaoTuyen() +
            "}";
    }
}
