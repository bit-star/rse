package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Photo.
 */
@Entity
@Table(name = "photo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_image")
    private String originalImage;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "width")
    private Long width;

    @Column(name = "height")
    private Long height;

    @Column(name = "rank_order")
    private Long rankOrder;

    @ManyToOne
    @JsonIgnoreProperties("photos")
    private Commodity commodity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public Photo originalImage(String originalImage) {
        this.originalImage = originalImage;
        return this;
    }

    public void setOriginalImage(String originalImage) {
        this.originalImage = originalImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Photo thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getWidth() {
        return width;
    }

    public Photo width(Long width) {
        this.width = width;
        return this;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public Photo height(Long height) {
        this.height = height;
        return this;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getRankOrder() {
        return rankOrder;
    }

    public Photo rankOrder(Long rankOrder) {
        this.rankOrder = rankOrder;
        return this;
    }

    public void setRankOrder(Long rankOrder) {
        this.rankOrder = rankOrder;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public Photo commodity(Commodity commodity) {
        this.commodity = commodity;
        return this;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Photo)) {
            return false;
        }
        return id != null && id.equals(((Photo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Photo{" +
            "id=" + getId() +
            ", originalImage='" + getOriginalImage() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", rankOrder=" + getRankOrder() +
            "}";
    }
}
