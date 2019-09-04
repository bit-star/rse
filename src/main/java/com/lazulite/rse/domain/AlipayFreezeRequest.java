package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AlipayFreezeRequest.
 */
@Entity
@Table(name = "alipay_freeze_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlipayFreezeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_title")
    private String orderTitle;

    @Column(name = "out_order_no")
    private String outOrderNo;

    @Column(name = "out_request_no")
    private String outRequestNo;

    @Column(name = "payee_user_id")
    private String payeeUserId;

    @Column(name = "payee_logon_id")
    private String payeeLogonId;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "amount")
    private String amount;

    @Column(name = "extra_param")
    private String extraParam;

    @Column(name = "notify_url")
    private String notifyUrl;

    @ManyToOne
    @JsonIgnoreProperties("alipayFreezeRequests")
    private UserOrder userOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public AlipayFreezeRequest orderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
        return this;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public AlipayFreezeRequest outOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
        return this;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public AlipayFreezeRequest outRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
        return this;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getPayeeUserId() {
        return payeeUserId;
    }

    public AlipayFreezeRequest payeeUserId(String payeeUserId) {
        this.payeeUserId = payeeUserId;
        return this;
    }

    public void setPayeeUserId(String payeeUserId) {
        this.payeeUserId = payeeUserId;
    }

    public String getPayeeLogonId() {
        return payeeLogonId;
    }

    public AlipayFreezeRequest payeeLogonId(String payeeLogonId) {
        this.payeeLogonId = payeeLogonId;
        return this;
    }

    public void setPayeeLogonId(String payeeLogonId) {
        this.payeeLogonId = payeeLogonId;
    }

    public String getProductCode() {
        return productCode;
    }

    public AlipayFreezeRequest productCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getAmount() {
        return amount;
    }

    public AlipayFreezeRequest amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExtraParam() {
        return extraParam;
    }

    public AlipayFreezeRequest extraParam(String extraParam) {
        this.extraParam = extraParam;
        return this;
    }

    public void setExtraParam(String extraParam) {
        this.extraParam = extraParam;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public AlipayFreezeRequest notifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public AlipayFreezeRequest userOrder(UserOrder userOrder) {
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
        if (!(o instanceof AlipayFreezeRequest)) {
            return false;
        }
        return id != null && id.equals(((AlipayFreezeRequest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AlipayFreezeRequest{" +
            "id=" + getId() +
            ", orderTitle='" + getOrderTitle() + "'" +
            ", outOrderNo='" + getOutOrderNo() + "'" +
            ", outRequestNo='" + getOutRequestNo() + "'" +
            ", payeeUserId='" + getPayeeUserId() + "'" +
            ", payeeLogonId='" + getPayeeLogonId() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", amount='" + getAmount() + "'" +
            ", extraParam='" + getExtraParam() + "'" +
            ", notifyUrl='" + getNotifyUrl() + "'" +
            "}";
    }
}
