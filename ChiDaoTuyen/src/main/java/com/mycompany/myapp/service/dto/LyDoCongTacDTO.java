package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.LyDoCongTac} entity.
 */
public class LyDoCongTacDTO implements Serializable {

    private Long id;

    private String maLyDo;

    private String tenLyDo;

    private String thuTuSX;

    private ChiDaoTuyenDTO chiDaoTuyen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLyDo() {
        return maLyDo;
    }

    public void setMaLyDo(String maLyDo) {
        this.maLyDo = maLyDo;
    }

    public String getTenLyDo() {
        return tenLyDo;
    }

    public void setTenLyDo(String tenLyDo) {
        this.tenLyDo = tenLyDo;
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
        if (!(o instanceof LyDoCongTacDTO)) {
            return false;
        }

        LyDoCongTacDTO lyDoCongTacDTO = (LyDoCongTacDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, lyDoCongTacDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LyDoCongTacDTO{" +
            "id=" + getId() +
            ", maLyDo='" + getMaLyDo() + "'" +
            ", tenLyDo='" + getTenLyDo() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            ", chiDaoTuyen=" + getChiDaoTuyen() +
            "}";
    }
}
