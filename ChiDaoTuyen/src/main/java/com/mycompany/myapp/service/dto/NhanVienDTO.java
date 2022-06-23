package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.NhanVien} entity.
 */
public class NhanVienDTO implements Serializable {

    private Long id;

    @NotNull
    private String maNhanVien;

    private String chucVu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NhanVienDTO)) {
            return false;
        }

        NhanVienDTO nhanVienDTO = (NhanVienDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, nhanVienDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhanVienDTO{" +
            "id=" + getId() +
            ", maNhanVien='" + getMaNhanVien() + "'" +
            ", chucVu='" + getChucVu() + "'" +
            "}";
    }
}
