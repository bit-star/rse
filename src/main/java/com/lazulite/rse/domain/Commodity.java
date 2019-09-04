package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.lazulite.rse.domain.enumeration.DeliveryMethod;

import com.lazulite.rse.domain.enumeration.CommodityStatus;

/**
 * A Commodity.
 */
@Entity
@Table(name = "commodity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "remark")
    private String remark;

    @Column(name = "day_rent", precision = 21, scale = 2)
    private BigDecimal dayRent;

    @Column(name = "weekly_rent", precision = 21, scale = 2)
    private BigDecimal weeklyRent;

    @Column(name = "monthly_rent", precision = 21, scale = 2)
    private BigDecimal monthlyRent;

    @Column(name = "deposit", precision = 21, scale = 2)
    private BigDecimal deposit;

    @Column(name = "rental_method")
    private String rentalMethod;

    @Column(name = "max_rent", precision = 21, scale = 2)
    private BigDecimal maxRent;

    @Column(name = "inventory")
    private Long inventory;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method")
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommodityStatus status;

    @Column(name = "lease_must_read")
    private String leaseMustRead;

    @Column(name = "desciption")
    private String desciption;

    @OneToMany(mappedBy = "commodity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(mappedBy = "commodity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Specification> specifications = new HashSet<>();

    @OneToMany(mappedBy = "commodity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("commodities")
    private Category category;

    @OneToMany(mappedBy = "commodity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderItem> orderItems = new HashSet<>();

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

    public Commodity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public Commodity remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getDayRent() {
        return dayRent;
    }

    public Commodity dayRent(BigDecimal dayRent) {
        this.dayRent = dayRent;
        return this;
    }

    public void setDayRent(BigDecimal dayRent) {
        this.dayRent = dayRent;
    }

    public BigDecimal getWeeklyRent() {
        return weeklyRent;
    }

    public Commodity weeklyRent(BigDecimal weeklyRent) {
        this.weeklyRent = weeklyRent;
        return this;
    }

    public void setWeeklyRent(BigDecimal weeklyRent) {
        this.weeklyRent = weeklyRent;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public Commodity monthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
        return this;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public Commodity deposit(BigDecimal deposit) {
        this.deposit = deposit;
        return this;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getRentalMethod() {
        return rentalMethod;
    }

    public Commodity rentalMethod(String rentalMethod) {
        this.rentalMethod = rentalMethod;
        return this;
    }

    public void setRentalMethod(String rentalMethod) {
        this.rentalMethod = rentalMethod;
    }

    public BigDecimal getMaxRent() {
        return maxRent;
    }

    public Commodity maxRent(BigDecimal maxRent) {
        this.maxRent = maxRent;
        return this;
    }

    public void setMaxRent(BigDecimal maxRent) {
        this.maxRent = maxRent;
    }

    public Long getInventory() {
        return inventory;
    }

    public Commodity inventory(Long inventory) {
        this.inventory = inventory;
        return this;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public Commodity deliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        return this;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public CommodityStatus getStatus() {
        return status;
    }

    public Commodity status(CommodityStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CommodityStatus status) {
        this.status = status;
    }

    public String getLeaseMustRead() {
        return leaseMustRead;
    }

    public Commodity leaseMustRead(String leaseMustRead) {
        this.leaseMustRead = leaseMustRead;
        return this;
    }

    public void setLeaseMustRead(String leaseMustRead) {
        this.leaseMustRead = leaseMustRead;
    }

    public String getDesciption() {
        return desciption;
    }

    public Commodity desciption(String desciption) {
        this.desciption = desciption;
        return this;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Commodity photos(Set<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public Commodity addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.setCommodity(this);
        return this;
    }

    public Commodity removePhoto(Photo photo) {
        this.photos.remove(photo);
        photo.setCommodity(null);
        return this;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Specification> getSpecifications() {
        return specifications;
    }

    public Commodity specifications(Set<Specification> specifications) {
        this.specifications = specifications;
        return this;
    }

    public Commodity addSpecification(Specification specification) {
        this.specifications.add(specification);
        specification.setCommodity(this);
        return this;
    }

    public Commodity removeSpecification(Specification specification) {
        this.specifications.remove(specification);
        specification.setCommodity(null);
        return this;
    }

    public void setSpecifications(Set<Specification> specifications) {
        this.specifications = specifications;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Commodity tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Commodity addTag(Tag tag) {
        this.tags.add(tag);
        tag.setCommodity(this);
        return this;
    }

    public Commodity removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.setCommodity(null);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public Commodity category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Commodity orderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public Commodity addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setCommodity(this);
        return this;
    }

    public Commodity removeOrderItem(OrderItem orderItem) {
        this.orderItems.remove(orderItem);
        orderItem.setCommodity(null);
        return this;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commodity)) {
            return false;
        }
        return id != null && id.equals(((Commodity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Commodity{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", remark='" + getRemark() + "'" +
            ", dayRent=" + getDayRent() +
            ", weeklyRent=" + getWeeklyRent() +
            ", monthlyRent=" + getMonthlyRent() +
            ", deposit=" + getDeposit() +
            ", rentalMethod='" + getRentalMethod() + "'" +
            ", maxRent=" + getMaxRent() +
            ", inventory=" + getInventory() +
            ", deliveryMethod='" + getDeliveryMethod() + "'" +
            ", status='" + getStatus() + "'" +
            ", leaseMustRead='" + getLeaseMustRead() + "'" +
            ", desciption='" + getDesciption() + "'" +
            "}";
    }
}
