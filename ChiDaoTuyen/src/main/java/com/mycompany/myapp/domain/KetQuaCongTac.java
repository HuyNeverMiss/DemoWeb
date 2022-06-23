package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A KetQuaCongTac.
 */
@Entity
@Table(name = "ket_qua_cong_tac")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KetQuaCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ma_ket_qua", nullable = false)
    private String maKetQua;

    @NotNull
    @Column(name = "ten_ket_qua", nullable = false)
    private String tenKetQua;

    @Column(name = "thu_tu_sx")
    private String thuTuSX;

    @OneToMany(mappedBy = "ketQuaCongTac")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "lyDoCongTac", "noiDenCongTac", "ketQuaCongTac", "kyThuatHoTro", "vatTuHoTro", "nhanVien" },
        allowSetters = true
    )
    private Set<ChiDaoTuyen> chiDaoTuyens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public KetQuaCongTac id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKetQua() {
        return this.maKetQua;
    }

    public KetQuaCongTac maKetQua(String maKetQua) {
        this.setMaKetQua(maKetQua);
        return this;
    }

    public void setMaKetQua(String maKetQua) {
        this.maKetQua = maKetQua;
    }

    public String getTenKetQua() {
        return this.tenKetQua;
    }

    public KetQuaCongTac tenKetQua(String tenKetQua) {
        this.setTenKetQua(tenKetQua);
        return this;
    }

    public void setTenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
    }

    public String getThuTuSX() {
        return this.thuTuSX;
    }

    public KetQuaCongTac thuTuSX(String thuTuSX) {
        this.setThuTuSX(thuTuSX);
        return this;
    }

    public void setThuTuSX(String thuTuSX) {
        this.thuTuSX = thuTuSX;
    }

    public Set<ChiDaoTuyen> getChiDaoTuyens() {
        return this.chiDaoTuyens;
    }

    public void setChiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        if (this.chiDaoTuyens != null) {
            this.chiDaoTuyens.forEach(i -> i.setKetQuaCongTac(null));
        }
        if (chiDaoTuyens != null) {
            chiDaoTuyens.forEach(i -> i.setKetQuaCongTac(this));
        }
        this.chiDaoTuyens = chiDaoTuyens;
    }

    public KetQuaCongTac chiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        this.setChiDaoTuyens(chiDaoTuyens);
        return this;
    }

    public KetQuaCongTac addChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.add(chiDaoTuyen);
        chiDaoTuyen.setKetQuaCongTac(this);
        return this;
    }

    public KetQuaCongTac removeChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.remove(chiDaoTuyen);
        chiDaoTuyen.setKetQuaCongTac(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KetQuaCongTac)) {
            return false;
        }
        return id != null && id.equals(((KetQuaCongTac) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KetQuaCongTac{" +
            "id=" + getId() +
            ", maKetQua='" + getMaKetQua() + "'" +
            ", tenKetQua='" + getTenKetQua() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            "}";
    }
}
