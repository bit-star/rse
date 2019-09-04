package com.lazulite.rse.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_order")
    private Long order;

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Commodity> commodities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrder() {
        return order;
    }

    public Category order(Long order) {
        this.order = order;
        return this;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Set<Commodity> getCommodities() {
        return commodities;
    }

    public Category commodities(Set<Commodity> commodities) {
        this.commodities = commodities;
        return this;
    }

    public Category addCommodity(Commodity commodity) {
        this.commodities.add(commodity);
        commodity.setCategory(this);
        return this;
    }

    public Category removeCommodity(Commodity commodity) {
        this.commodities.remove(commodity);
        commodity.setCategory(null);
        return this;
    }

    public void setCommodities(Set<Commodity> commodities) {
        this.commodities = commodities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", order=" + getOrder() +
            "}";
    }
}
