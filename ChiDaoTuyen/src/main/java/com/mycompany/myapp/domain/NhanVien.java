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
 * A NhanVien.
 */
@Entity
@Table(name = "nhan_vien")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NhanVien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ma_nhan_vien", nullable = false)
    private String maNhanVien;

    @Column(name = "chuc_vu")
    private String chucVu;

    @OneToMany(mappedBy = "nhanVien")
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

    public NhanVien id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return this.maNhanVien;
    }

    public NhanVien maNhanVien(String maNhanVien) {
        this.setMaNhanVien(maNhanVien);
        return this;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getChucVu() {
        return this.chucVu;
    }

    public NhanVien chucVu(String chucVu) {
        this.setChucVu(chucVu);
        return this;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public Set<ChiDaoTuyen> getChiDaoTuyens() {
        return this.chiDaoTuyens;
    }

    public void setChiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        if (this.chiDaoTuyens != null) {
            this.chiDaoTuyens.forEach(i -> i.setNhanVien(null));
        }
        if (chiDaoTuyens != null) {
            chiDaoTuyens.forEach(i -> i.setNhanVien(this));
        }
        this.chiDaoTuyens = chiDaoTuyens;
    }

    public NhanVien chiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        this.setChiDaoTuyens(chiDaoTuyens);
        return this;
    }

    public NhanVien addChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.add(chiDaoTuyen);
        chiDaoTuyen.setNhanVien(this);
        return this;
    }

    public NhanVien removeChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.remove(chiDaoTuyen);
        chiDaoTuyen.setNhanVien(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NhanVien)) {
            return false;
        }
        return id != null && id.equals(((NhanVien) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhanVien{" +
            "id=" + getId() +
            ", maNhanVien='" + getMaNhanVien() + "'" +
            ", chucVu='" + getChucVu() + "'" +
            "}";
    }
}
