package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.KetQuaCongTac} entity.
 */
public class KetQuaCongTacDTO implements Serializable {

    private Long id;

    @NotNull
    private String maKetQua;

    @NotNull
    private String tenKetQua;

    private String thuTuSX;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKetQua() {
        return maKetQua;
    }

    public void setMaKetQua(String maKetQua) {
        this.maKetQua = maKetQua;
    }

    public String getTenKetQua() {
        return tenKetQua;
    }

    public void setTenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
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
        if (!(o instanceof KetQuaCongTacDTO)) {
            return false;
        }

        KetQuaCongTacDTO ketQuaCongTacDTO = (KetQuaCongTacDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ketQuaCongTacDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KetQuaCongTacDTO{" +
            "id=" + getId() +
            ", maKetQua='" + getMaKetQua() + "'" +
            ", tenKetQua='" + getTenKetQua() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            "}";
    }
}
