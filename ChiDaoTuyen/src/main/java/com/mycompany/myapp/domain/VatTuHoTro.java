package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VatTuHoTro.
 */
@Entity
@Table(name = "vat_tu_ho_tro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VatTuHoTro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_vat_tu")
    private String maVatTu;

    @Column(name = "ten_vat_tu")
    private String tenVatTu;

    @Column(name = "thu_tu_sx")
    private String thuTuSX;

    @OneToOne
    @JoinColumn(unique = true)
    private ChiDaoTuyen chiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VatTuHoTro id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaVatTu() {
        return this.maVatTu;
    }

    public VatTuHoTro maVatTu(String maVatTu) {
        this.setMaVatTu(maVatTu);
        return this;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return this.tenVatTu;
    }

    public VatTuHoTro tenVatTu(String tenVatTu) {
        this.setTenVatTu(tenVatTu);
        return this;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public String getThuTuSX() {
        return this.thuTuSX;
    }

    public VatTuHoTro thuTuSX(String thuTuSX) {
        this.setThuTuSX(thuTuSX);
        return this;
    }

    public void setThuTuSX(String thuTuSX) {
        this.thuTuSX = thuTuSX;
    }

    public ChiDaoTuyen getChiDaoTuyen() {
        return this.chiDaoTuyen;
    }

    public void setChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyen = chiDaoTuyen;
    }

    public VatTuHoTro chiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.setChiDaoTuyen(chiDaoTuyen);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VatTuHoTro)) {
            return false;
        }
        return id != null && id.equals(((VatTuHoTro) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VatTuHoTro{" +
            "id=" + getId() +
            ", maVatTu='" + getMaVatTu() + "'" +
            ", tenVatTu='" + getTenVatTu() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            "}";
    }
}
