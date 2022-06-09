package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.VatTuHoTro} entity.
 */
public class VatTuHoTroDTO implements Serializable {

    private Long id;

    private String maVatTu;

    private String tenVatTu;

    private String thuTuSX;

    private ChiDaoTuyenDTO chiDaoTuyen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
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
        if (!(o instanceof VatTuHoTroDTO)) {
            return false;
        }

        VatTuHoTroDTO vatTuHoTroDTO = (VatTuHoTroDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vatTuHoTroDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VatTuHoTroDTO{" +
            "id=" + getId() +
            ", maVatTu='" + getMaVatTu() + "'" +
            ", tenVatTu='" + getTenVatTu() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            ", chiDaoTuyen=" + getChiDaoTuyen() +
            "}";
    }
}
