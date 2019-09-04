package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A ItemLeaseCycle.
 */
@Entity
@Table(name = "item_lease_cycle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemLeaseCycle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "inventory")
    private Long inventory;

    @Column(name = "number_of_periods")
    private Long numberOfPeriods;

    @Column(name = "deposit", precision = 21, scale = 2)
    private BigDecimal deposit;

    @Column(name = "credit_exemption", precision = 21, scale = 2)
    private BigDecimal creditExemption;

    @Column(name = "rent_received_number_of_periods")
    private Long rentReceivedNumberOfPeriods;

    @Column(name = "debited_amount", precision = 21, scale = 2)
    private BigDecimal debitedAmount;

    @Column(name = "next_bill_day")
    private Instant nextBillDay;

    @ManyToOne
    @JsonIgnoreProperties("itemLeaseCycles")
    private OrderItem orderItem;

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

    public ItemLeaseCycle name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public ItemLeaseCycle startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public ItemLeaseCycle endTime(Instant endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Long getInventory() {
        return inventory;
    }

    public ItemLeaseCycle inventory(Long inventory) {
        this.inventory = inventory;
        return this;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Long getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public ItemLeaseCycle numberOfPeriods(Long numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
        return this;
    }

    public void setNumberOfPeriods(Long numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public ItemLeaseCycle deposit(BigDecimal deposit) {
        this.deposit = deposit;
        return this;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getCreditExemption() {
        return creditExemption;
    }

    public ItemLeaseCycle creditExemption(BigDecimal creditExemption) {
        this.creditExemption = creditExemption;
        return this;
    }

    public void setCreditExemption(BigDecimal creditExemption) {
        this.creditExemption = creditExemption;
    }

    public Long getRentReceivedNumberOfPeriods() {
        return rentReceivedNumberOfPeriods;
    }

    public ItemLeaseCycle rentReceivedNumberOfPeriods(Long rentReceivedNumberOfPeriods) {
        this.rentReceivedNumberOfPeriods = rentReceivedNumberOfPeriods;
        return this;
    }

    public void setRentReceivedNumberOfPeriods(Long rentReceivedNumberOfPeriods) {
        this.rentReceivedNumberOfPeriods = rentReceivedNumberOfPeriods;
    }

    public BigDecimal getDebitedAmount() {
        return debitedAmount;
    }

    public ItemLeaseCycle debitedAmount(BigDecimal debitedAmount) {
        this.debitedAmount = debitedAmount;
        return this;
    }

    public void setDebitedAmount(BigDecimal debitedAmount) {
        this.debitedAmount = debitedAmount;
    }

    public Instant getNextBillDay() {
        return nextBillDay;
    }

    public ItemLeaseCycle nextBillDay(Instant nextBillDay) {
        this.nextBillDay = nextBillDay;
        return this;
    }

    public void setNextBillDay(Instant nextBillDay) {
        this.nextBillDay = nextBillDay;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public ItemLeaseCycle orderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        return this;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemLeaseCycle)) {
            return false;
        }
        return id != null && id.equals(((ItemLeaseCycle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ItemLeaseCycle{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", inventory=" + getInventory() +
            ", numberOfPeriods=" + getNumberOfPeriods() +
            ", deposit=" + getDeposit() +
            ", creditExemption=" + getCreditExemption() +
            ", rentReceivedNumberOfPeriods=" + getRentReceivedNumberOfPeriods() +
            ", debitedAmount=" + getDebitedAmount() +
            ", nextBillDay='" + getNextBillDay() + "'" +
            "}";
    }
}
