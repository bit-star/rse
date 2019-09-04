package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.lazulite.rse.domain.enumeration.RightsProtectionStatus;

/**
 * A OrderItem.
 */
@Entity
@Table(name = "order_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_price", precision = 21, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "actually_paid", precision = 21, scale = 2)
    private BigDecimal actuallyPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "rights_protection_status")
    private RightsProtectionStatus rightsProtectionStatus;

    @OneToMany(mappedBy = "orderItem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemLeaseCycle> itemLeaseCycles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("orderItems")
    private Commodity commodity;

    @ManyToOne
    @JsonIgnoreProperties("orderItems")
    private UserOrder userOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public OrderItem unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getActuallyPaid() {
        return actuallyPaid;
    }

    public OrderItem actuallyPaid(BigDecimal actuallyPaid) {
        this.actuallyPaid = actuallyPaid;
        return this;
    }

    public void setActuallyPaid(BigDecimal actuallyPaid) {
        this.actuallyPaid = actuallyPaid;
    }

    public RightsProtectionStatus getRightsProtectionStatus() {
        return rightsProtectionStatus;
    }

    public OrderItem rightsProtectionStatus(RightsProtectionStatus rightsProtectionStatus) {
        this.rightsProtectionStatus = rightsProtectionStatus;
        return this;
    }

    public void setRightsProtectionStatus(RightsProtectionStatus rightsProtectionStatus) {
        this.rightsProtectionStatus = rightsProtectionStatus;
    }

    public Set<ItemLeaseCycle> getItemLeaseCycles() {
        return itemLeaseCycles;
    }

    public OrderItem itemLeaseCycles(Set<ItemLeaseCycle> itemLeaseCycles) {
        this.itemLeaseCycles = itemLeaseCycles;
        return this;
    }

    public OrderItem addItemLeaseCycle(ItemLeaseCycle itemLeaseCycle) {
        this.itemLeaseCycles.add(itemLeaseCycle);
        itemLeaseCycle.setOrderItem(this);
        return this;
    }

    public OrderItem removeItemLeaseCycle(ItemLeaseCycle itemLeaseCycle) {
        this.itemLeaseCycles.remove(itemLeaseCycle);
        itemLeaseCycle.setOrderItem(null);
        return this;
    }

    public void setItemLeaseCycles(Set<ItemLeaseCycle> itemLeaseCycles) {
        this.itemLeaseCycles = itemLeaseCycles;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public OrderItem commodity(Commodity commodity) {
        this.commodity = commodity;
        return this;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public OrderItem userOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
        return this;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItem)) {
            return false;
        }
        return id != null && id.equals(((OrderItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + getId() +
            ", unitPrice=" + getUnitPrice() +
            ", actuallyPaid=" + getActuallyPaid() +
            ", rightsProtectionStatus='" + getRightsProtectionStatus() + "'" +
            "}";
    }
}
