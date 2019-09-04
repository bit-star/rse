package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.lazulite.rse.domain.enumeration.OrderStatus;

/**
 * A UserOrder.
 */
@Entity
@Table(name = "user_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "request_no")
    private String requestNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "address")
    private String address;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "logistics_company")
    private String logisticsCompany;

    @Column(name = "shipment_number")
    private String shipmentNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "freight", precision = 21, scale = 2)
    private BigDecimal freight;

    @Column(name = "description")
    private String description;

    @Column(name = "processing_opinions")
    private String processingOpinions;

    @OneToMany(mappedBy = "userOrder")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToMany(mappedBy = "userOrder")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AlipayFreezeRequest> alipayFreezeRequests = new HashSet<>();

    @OneToMany(mappedBy = "userOrder")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AlipayFreezeResponse> alipayFreezeResponses = new HashSet<>();

    @OneToMany(mappedBy = "userOrder")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AlipayFundAuthInfo> alipayFundAuthInfos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("userOrders")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public UserOrder orderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public UserOrder requestNo(String requestNo) {
        this.requestNo = requestNo;
        return this;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public UserOrder status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getReceiver() {
        return receiver;
    }

    public UserOrder receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public UserOrder mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public UserOrder province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public UserOrder city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public UserOrder region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public UserOrder address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoreName() {
        return storeName;
    }

    public UserOrder storeName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public UserOrder logisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
        return this;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public UserOrder shipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
        return this;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public UserOrder paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public UserOrder freight(BigDecimal freight) {
        this.freight = freight;
        return this;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getDescription() {
        return description;
    }

    public UserOrder description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessingOpinions() {
        return processingOpinions;
    }

    public UserOrder processingOpinions(String processingOpinions) {
        this.processingOpinions = processingOpinions;
        return this;
    }

    public void setProcessingOpinions(String processingOpinions) {
        this.processingOpinions = processingOpinions;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public UserOrder orderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public UserOrder addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setUserOrder(this);
        return this;
    }

    public UserOrder removeOrderItem(OrderItem orderItem) {
        this.orderItems.remove(orderItem);
        orderItem.setUserOrder(null);
        return this;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<AlipayFreezeRequest> getAlipayFreezeRequests() {
        return alipayFreezeRequests;
    }

    public UserOrder alipayFreezeRequests(Set<AlipayFreezeRequest> alipayFreezeRequests) {
        this.alipayFreezeRequests = alipayFreezeRequests;
        return this;
    }

    public UserOrder addAlipayFreezeRequest(AlipayFreezeRequest alipayFreezeRequest) {
        this.alipayFreezeRequests.add(alipayFreezeRequest);
        alipayFreezeRequest.setUserOrder(this);
        return this;
    }

    public UserOrder removeAlipayFreezeRequest(AlipayFreezeRequest alipayFreezeRequest) {
        this.alipayFreezeRequests.remove(alipayFreezeRequest);
        alipayFreezeRequest.setUserOrder(null);
        return this;
    }

    public void setAlipayFreezeRequests(Set<AlipayFreezeRequest> alipayFreezeRequests) {
        this.alipayFreezeRequests = alipayFreezeRequests;
    }

    public Set<AlipayFreezeResponse> getAlipayFreezeResponses() {
        return alipayFreezeResponses;
    }

    public UserOrder alipayFreezeResponses(Set<AlipayFreezeResponse> alipayFreezeResponses) {
        this.alipayFreezeResponses = alipayFreezeResponses;
        return this;
    }

    public UserOrder addAlipayFreezeResponse(AlipayFreezeResponse alipayFreezeResponse) {
        this.alipayFreezeResponses.add(alipayFreezeResponse);
        alipayFreezeResponse.setUserOrder(this);
        return this;
    }

    public UserOrder removeAlipayFreezeResponse(AlipayFreezeResponse alipayFreezeResponse) {
        this.alipayFreezeResponses.remove(alipayFreezeResponse);
        alipayFreezeResponse.setUserOrder(null);
        return this;
    }

    public void setAlipayFreezeResponses(Set<AlipayFreezeResponse> alipayFreezeResponses) {
        this.alipayFreezeResponses = alipayFreezeResponses;
    }

    public Set<AlipayFundAuthInfo> getAlipayFundAuthInfos() {
        return alipayFundAuthInfos;
    }

    public UserOrder alipayFundAuthInfos(Set<AlipayFundAuthInfo> alipayFundAuthInfos) {
        this.alipayFundAuthInfos = alipayFundAuthInfos;
        return this;
    }

    public UserOrder addAlipayFundAuthInfo(AlipayFundAuthInfo alipayFundAuthInfo) {
        this.alipayFundAuthInfos.add(alipayFundAuthInfo);
        alipayFundAuthInfo.setUserOrder(this);
        return this;
    }

    public UserOrder removeAlipayFundAuthInfo(AlipayFundAuthInfo alipayFundAuthInfo) {
        this.alipayFundAuthInfos.remove(alipayFundAuthInfo);
        alipayFundAuthInfo.setUserOrder(null);
        return this;
    }

    public void setAlipayFundAuthInfos(Set<AlipayFundAuthInfo> alipayFundAuthInfos) {
        this.alipayFundAuthInfos = alipayFundAuthInfos;
    }

    public User getUser() {
        return user;
    }

    public UserOrder user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserOrder)) {
            return false;
        }
        return id != null && id.equals(((UserOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
            "id=" + getId() +
            ", orderNo='" + getOrderNo() + "'" +
            ", requestNo='" + getRequestNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", region='" + getRegion() + "'" +
            ", address='" + getAddress() + "'" +
            ", storeName='" + getStoreName() + "'" +
            ", logisticsCompany='" + getLogisticsCompany() + "'" +
            ", shipmentNumber='" + getShipmentNumber() + "'" +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", freight=" + getFreight() +
            ", description='" + getDescription() + "'" +
            ", processingOpinions='" + getProcessingOpinions() + "'" +
            "}";
    }
}
