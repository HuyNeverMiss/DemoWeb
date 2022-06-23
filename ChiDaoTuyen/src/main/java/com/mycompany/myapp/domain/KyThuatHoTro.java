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
 * A KyThuatHoTro.
 */
@Entity
@Table(name = "ky_thuat_ho_tro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KyThuatHoTro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ma_ky_thuat", nullable = false)
    private String maKyThuat;

    @NotNull
    @Column(name = "ten_ky_thuat", nullable = false)
    private String tenKyThuat;

    @Column(name = "thu_tu_sx")
    private String thuTuSX;

    @OneToMany(mappedBy = "kyThuatHoTro")
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

    public KyThuatHoTro id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKyThuat() {
        return this.maKyThuat;
    }

    public KyThuatHoTro maKyThuat(String maKyThuat) {
        this.setMaKyThuat(maKyThuat);
        return this;
    }

    public void setMaKyThuat(String maKyThuat) {
        this.maKyThuat = maKyThuat;
    }

    public String getTenKyThuat() {
        return this.tenKyThuat;
    }

    public KyThuatHoTro tenKyThuat(String tenKyThuat) {
        this.setTenKyThuat(tenKyThuat);
        return this;
    }

    public void setTenKyThuat(String tenKyThuat) {
        this.tenKyThuat = tenKyThuat;
    }

    public String getThuTuSX() {
        return this.thuTuSX;
    }

    public KyThuatHoTro thuTuSX(String thuTuSX) {
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
            this.chiDaoTuyens.forEach(i -> i.setKyThuatHoTro(null));
        }
        if (chiDaoTuyens != null) {
            chiDaoTuyens.forEach(i -> i.setKyThuatHoTro(this));
        }
        this.chiDaoTuyens = chiDaoTuyens;
    }

    public KyThuatHoTro chiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        this.setChiDaoTuyens(chiDaoTuyens);
        return this;
    }

    public KyThuatHoTro addChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.add(chiDaoTuyen);
        chiDaoTuyen.setKyThuatHoTro(this);
        return this;
    }

    public KyThuatHoTro removeChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.remove(chiDaoTuyen);
        chiDaoTuyen.setKyThuatHoTro(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KyThuatHoTro)) {
            return false;
        }
        return id != null && id.equals(((KyThuatHoTro) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KyThuatHoTro{" +
            "id=" + getId() +
            ", maKyThuat='" + getMaKyThuat() + "'" +
            ", tenKyThuat='" + getTenKyThuat() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            "}";
    }
}
