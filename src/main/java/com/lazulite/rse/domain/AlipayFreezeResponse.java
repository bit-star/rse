package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AlipayFreezeResponse.
 */
@Entity
@Table(name = "alipay_freeze_response")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlipayFreezeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "msg")
    private String msg;

    @Column(name = "sub_code")
    private String subCode;

    @Column(name = "sub_msg")
    private String subMsg;

    @Column(name = "body")
    private String body;

    @Column(name = "params_str")
    private String paramsStr;

    @Column(name = "amount")
    private String amount;

    @Column(name = "auth_no")
    private String authNo;

    @Column(name = "credit_amount")
    private String creditAmount;

    @Column(name = "fund_amount")
    private String fundAmount;

    @Column(name = "gmt_trans")
    private String gmtTrans;

    @Column(name = "operation_id")
    private String operationId;

    @Column(name = "out_order_no")
    private String outOrderNo;

    @Column(name = "out_request_no")
    private String outRequestNo;

    @Column(name = "payer_user_id")
    private String payerUserId;

    @Column(name = "pre_auth_type")
    private String preAuthType;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("alipayFreezeResponses")
    private UserOrder userOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public AlipayFreezeResponse code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public AlipayFreezeResponse msg(String msg) {
        this.msg = msg;
        return this;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public AlipayFreezeResponse subCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public AlipayFreezeResponse subMsg(String subMsg) {
        this.subMsg = subMsg;
        return this;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public AlipayFreezeResponse body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getParamsStr() {
        return paramsStr;
    }

    public AlipayFreezeResponse paramsStr(String paramsStr) {
        this.paramsStr = paramsStr;
        return this;
    }

    public void setParamsStr(String paramsStr) {
        this.paramsStr = paramsStr;
    }

    public String getAmount() {
        return amount;
    }

    public AlipayFreezeResponse amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAuthNo() {
        return authNo;
    }

    public AlipayFreezeResponse authNo(String authNo) {
        this.authNo = authNo;
        return this;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public AlipayFreezeResponse creditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
        return this;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public AlipayFreezeResponse fundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
        return this;
    }

    public void setFundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getGmtTrans() {
        return gmtTrans;
    }

    public AlipayFreezeResponse gmtTrans(String gmtTrans) {
        this.gmtTrans = gmtTrans;
        return this;
    }

    public void setGmtTrans(String gmtTrans) {
        this.gmtTrans = gmtTrans;
    }

    public String getOperationId() {
        return operationId;
    }

    public AlipayFreezeResponse operationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public AlipayFreezeResponse outOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
        return this;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public AlipayFreezeResponse outRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
        return this;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getPayerUserId() {
        return payerUserId;
    }

    public AlipayFreezeResponse payerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
        return this;
    }

    public void setPayerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
    }

    public String getPreAuthType() {
        return preAuthType;
    }

    public AlipayFreezeResponse preAuthType(String preAuthType) {
        this.preAuthType = preAuthType;
        return this;
    }

    public void setPreAuthType(String preAuthType) {
        this.preAuthType = preAuthType;
    }

    public String getStatus() {
        return status;
    }

    public AlipayFreezeResponse status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public AlipayFreezeResponse userOrder(UserOrder userOrder) {
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
        if (!(o instanceof AlipayFreezeResponse)) {
            return false;
        }
        return id != null && id.equals(((AlipayFreezeResponse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AlipayFreezeResponse{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", msg='" + getMsg() + "'" +
            ", subCode='" + getSubCode() + "'" +
            ", subMsg='" + getSubMsg() + "'" +
            ", body='" + getBody() + "'" +
            ", paramsStr='" + getParamsStr() + "'" +
            ", amount='" + getAmount() + "'" +
            ", authNo='" + getAuthNo() + "'" +
            ", creditAmount='" + getCreditAmount() + "'" +
            ", fundAmount='" + getFundAmount() + "'" +
            ", gmtTrans='" + getGmtTrans() + "'" +
            ", operationId='" + getOperationId() + "'" +
            ", outOrderNo='" + getOutOrderNo() + "'" +
            ", outRequestNo='" + getOutRequestNo() + "'" +
            ", payerUserId='" + getPayerUserId() + "'" +
            ", preAuthType='" + getPreAuthType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
