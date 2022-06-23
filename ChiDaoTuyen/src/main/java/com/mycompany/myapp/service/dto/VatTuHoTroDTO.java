package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.VatTuHoTro} entity.
 */
public class VatTuHoTroDTO implements Serializable {

    private Long id;

    @NotNull
    private String maVatTu;

    @NotNull
    private String tenVatTu;

    private String thuTuSX;

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
            "}";
    }
}
