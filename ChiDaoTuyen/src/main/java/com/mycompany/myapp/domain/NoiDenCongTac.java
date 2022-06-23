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
 * A NoiDenCongTac.
 */
@Entity
@Table(name = "noi_den_cong_tac")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoiDenCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ma_noi_den", nullable = false)
    private String maNoiDen;

    @NotNull
    @Column(name = "ten_noi_den", nullable = false)
    private String tenNoiDen;

    @Column(name = "thu_tu_sx")
    private String thuTuSX;

    @OneToMany(mappedBy = "noiDenCongTac")
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

    public NoiDenCongTac id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNoiDen() {
        return this.maNoiDen;
    }

    public NoiDenCongTac maNoiDen(String maNoiDen) {
        this.setMaNoiDen(maNoiDen);
        return this;
    }

    public void setMaNoiDen(String maNoiDen) {
        this.maNoiDen = maNoiDen;
    }

    public String getTenNoiDen() {
        return this.tenNoiDen;
    }

    public NoiDenCongTac tenNoiDen(String tenNoiDen) {
        this.setTenNoiDen(tenNoiDen);
        return this;
    }

    public void setTenNoiDen(String tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
    }

    public String getThuTuSX() {
        return this.thuTuSX;
    }

    public NoiDenCongTac thuTuSX(String thuTuSX) {
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
            this.chiDaoTuyens.forEach(i -> i.setNoiDenCongTac(null));
        }
        if (chiDaoTuyens != null) {
            chiDaoTuyens.forEach(i -> i.setNoiDenCongTac(this));
        }
        this.chiDaoTuyens = chiDaoTuyens;
    }

    public NoiDenCongTac chiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        this.setChiDaoTuyens(chiDaoTuyens);
        return this;
    }

    public NoiDenCongTac addChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.add(chiDaoTuyen);
        chiDaoTuyen.setNoiDenCongTac(this);
        return this;
    }

    public NoiDenCongTac removeChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.remove(chiDaoTuyen);
        chiDaoTuyen.setNoiDenCongTac(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoiDenCongTac)) {
            return false;
        }
        return id != null && id.equals(((NoiDenCongTac) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoiDenCongTac{" +
            "id=" + getId() +
            ", maNoiDen='" + getMaNoiDen() + "'" +
            ", tenNoiDen='" + getTenNoiDen() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            "}";
    }
}
