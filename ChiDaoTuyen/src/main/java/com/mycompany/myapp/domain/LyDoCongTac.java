package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/*Xác định lý do công tác với các thuộc tính như id, ma_ly_do, ten_ly_do, thu_tu_sx */
/**
 * A LyDoCongTac.
 */
@Entity
@Table(name = "ly_do_cong_tac")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LyDoCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_ly_do")
    private String maLyDo;

    @Column(name = "ten_ly_do")
    private String tenLyDo;

    @Column(name = "thu_tu_sx")
    private String thuTuSX;

    @OneToOne
    @JoinColumn(unique = true)
    private ChiDaoTuyen chiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LyDoCongTac id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLyDo() {
        return this.maLyDo;
    }

    public LyDoCongTac maLyDo(String maLyDo) {
        this.setMaLyDo(maLyDo);
        return this;
    }

    public void setMaLyDo(String maLyDo) {
        this.maLyDo = maLyDo;
    }

    public String getTenLyDo() {
        return this.tenLyDo;
    }

    public LyDoCongTac tenLyDo(String tenLyDo) {
        this.setTenLyDo(tenLyDo);
        return this;
    }

    public void setTenLyDo(String tenLyDo) {
        this.tenLyDo = tenLyDo;
    }

    public String getThuTuSX() {
        return this.thuTuSX;
    }

    public LyDoCongTac thuTuSX(String thuTuSX) {
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

    public LyDoCongTac chiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.setChiDaoTuyen(chiDaoTuyen);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LyDoCongTac)) {
            return false;
        }
        return id != null && id.equals(((LyDoCongTac) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LyDoCongTac{" +
            "id=" + getId() +
            ", maLyDo='" + getMaLyDo() + "'" +
            ", tenLyDo='" + getTenLyDo() + "'" +
            ", thuTuSX='" + getThuTuSX() + "'" +
            "}";
    }
}
