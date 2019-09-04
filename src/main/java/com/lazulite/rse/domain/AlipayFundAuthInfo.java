package com.lazulite.rse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AlipayFundAuthInfo.
 */
@Entity
@Table(name = "alipay_fund_auth_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlipayFundAuthInfo implements Serializable {

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

    @Column(name = "extra_param")
    private String extraParam;

    @Column(name = "fund_amount")
    private String fundAmount;

    @Column(name = "gmt_create_str")
    private String gmtCreateStr;

    @Column(name = "gmt_trans_str")
    private String gmtTransStr;

    @Column(name = "operation_id")
    private String operationId;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "order_title")
    private String orderTitle;

    @Column(name = "out_order_no")
    private String outOrderNo;

    @Column(name = "out_request_no")
    private String outRequestNo;

    @Column(name = "payer_logon_id")
    private String payerLogonId;

    @Column(name = "payer_user_id")
    private String payerUserId;

    @Column(name = "pre_auth_type")
    private String preAuthType;

    @Column(name = "remark")
    private String remark;

    @Column(name = "rest_amount")
    private String restAmount;

    @Column(name = "rest_credit_amount")
    private String restCreditAmount;

    @Column(name = "rest_fund_amount")
    private String restFundAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "total_freeze_amount")
    private String totalFreezeAmount;

    @Column(name = "total_freeze_credit_amount")
    private String totalFreezeCreditAmount;

    @Column(name = "total_freeze_fund_amount")
    private String totalFreezeFundAmount;

    @Column(name = "total_pay_amount")
    private String totalPayAmount;

    @Column(name = "total_pay_credit_amount")
    private String totalPayCreditAmount;

    @Column(name = "total_pay_fund_amount")
    private String totalPayFundAmount;

    @Column(name = "trans_currency")
    private String transCurrency;

    @ManyToOne
    @JsonIgnoreProperties("alipayFundAuthInfos")
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

    public AlipayFundAuthInfo code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public AlipayFundAuthInfo msg(String msg) {
        this.msg = msg;
        return this;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public AlipayFundAuthInfo subCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public AlipayFundAuthInfo subMsg(String subMsg) {
        this.subMsg = subMsg;
        return this;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public AlipayFundAuthInfo body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getParamsStr() {
        return paramsStr;
    }

    public AlipayFundAuthInfo paramsStr(String paramsStr) {
        this.paramsStr = paramsStr;
        return this;
    }

    public void setParamsStr(String paramsStr) {
        this.paramsStr = paramsStr;
    }

    public String getAmount() {
        return amount;
    }

    public AlipayFundAuthInfo amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAuthNo() {
        return authNo;
    }

    public AlipayFundAuthInfo authNo(String authNo) {
        this.authNo = authNo;
        return this;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public AlipayFundAuthInfo creditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
        return this;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getExtraParam() {
        return extraParam;
    }

    public AlipayFundAuthInfo extraParam(String extraParam) {
        this.extraParam = extraParam;
        return this;
    }

    public void setExtraParam(String extraParam) {
        this.extraParam = extraParam;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public AlipayFundAuthInfo fundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
        return this;
    }

    public void setFundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getGmtCreateStr() {
        return gmtCreateStr;
    }

    public AlipayFundAuthInfo gmtCreateStr(String gmtCreateStr) {
        this.gmtCreateStr = gmtCreateStr;
        return this;
    }

    public void setGmtCreateStr(String gmtCreateStr) {
        this.gmtCreateStr = gmtCreateStr;
    }

    public String getGmtTransStr() {
        return gmtTransStr;
    }

    public AlipayFundAuthInfo gmtTransStr(String gmtTransStr) {
        this.gmtTransStr = gmtTransStr;
        return this;
    }

    public void setGmtTransStr(String gmtTransStr) {
        this.gmtTransStr = gmtTransStr;
    }

    public String getOperationId() {
        return operationId;
    }

    public AlipayFundAuthInfo operationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationType() {
        return operationType;
    }

    public AlipayFundAuthInfo operationType(String operationType) {
        this.operationType = operationType;
        return this;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public AlipayFundAuthInfo orderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
        return this;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public AlipayFundAuthInfo outOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
        return this;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public AlipayFundAuthInfo outRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
        return this;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getPayerLogonId() {
        return payerLogonId;
    }

    public AlipayFundAuthInfo payerLogonId(String payerLogonId) {
        this.payerLogonId = payerLogonId;
        return this;
    }

    public void setPayerLogonId(String payerLogonId) {
        this.payerLogonId = payerLogonId;
    }

    public String getPayerUserId() {
        return payerUserId;
    }

    public AlipayFundAuthInfo payerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
        return this;
    }

    public void setPayerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
    }

    public String getPreAuthType() {
        return preAuthType;
    }

    public AlipayFundAuthInfo preAuthType(String preAuthType) {
        this.preAuthType = preAuthType;
        return this;
    }

    public void setPreAuthType(String preAuthType) {
        this.preAuthType = preAuthType;
    }

    public String getRemark() {
        return remark;
    }

    public AlipayFundAuthInfo remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRestAmount() {
        return restAmount;
    }

    public AlipayFundAuthInfo restAmount(String restAmount) {
        this.restAmount = restAmount;
        return this;
    }

    public void setRestAmount(String restAmount) {
        this.restAmount = restAmount;
    }

    public String getRestCreditAmount() {
        return restCreditAmount;
    }

    public AlipayFundAuthInfo restCreditAmount(String restCreditAmount) {
        this.restCreditAmount = restCreditAmount;
        return this;
    }

    public void setRestCreditAmount(String restCreditAmount) {
        this.restCreditAmount = restCreditAmount;
    }

    public String getRestFundAmount() {
        return restFundAmount;
    }

    public AlipayFundAuthInfo restFundAmount(String restFundAmount) {
        this.restFundAmount = restFundAmount;
        return this;
    }

    public void setRestFundAmount(String restFundAmount) {
        this.restFundAmount = restFundAmount;
    }

    public String getStatus() {
        return status;
    }

    public AlipayFundAuthInfo status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalFreezeAmount() {
        return totalFreezeAmount;
    }

    public AlipayFundAuthInfo totalFreezeAmount(String totalFreezeAmount) {
        this.totalFreezeAmount = totalFreezeAmount;
        return this;
    }

    public void setTotalFreezeAmount(String totalFreezeAmount) {
        this.totalFreezeAmount = totalFreezeAmount;
    }

    public String getTotalFreezeCreditAmount() {
        return totalFreezeCreditAmount;
    }

    public AlipayFundAuthInfo totalFreezeCreditAmount(String totalFreezeCreditAmount) {
        this.totalFreezeCreditAmount = totalFreezeCreditAmount;
        return this;
    }

    public void setTotalFreezeCreditAmount(String totalFreezeCreditAmount) {
        this.totalFreezeCreditAmount = totalFreezeCreditAmount;
    }

    public String getTotalFreezeFundAmount() {
        return totalFreezeFundAmount;
    }

    public AlipayFundAuthInfo totalFreezeFundAmount(String totalFreezeFundAmount) {
        this.totalFreezeFundAmount = totalFreezeFundAmount;
        return this;
    }

    public void setTotalFreezeFundAmount(String totalFreezeFundAmount) {
        this.totalFreezeFundAmount = totalFreezeFundAmount;
    }

    public String getTotalPayAmount() {
        return totalPayAmount;
    }

    public AlipayFundAuthInfo totalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
        return this;
    }

    public void setTotalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getTotalPayCreditAmount() {
        return totalPayCreditAmount;
    }

    public AlipayFundAuthInfo totalPayCreditAmount(String totalPayCreditAmount) {
        this.totalPayCreditAmount = totalPayCreditAmount;
        return this;
    }

    public void setTotalPayCreditAmount(String totalPayCreditAmount) {
        this.totalPayCreditAmount = totalPayCreditAmount;
    }

    public String getTotalPayFundAmount() {
        return totalPayFundAmount;
    }

    public AlipayFundAuthInfo totalPayFundAmount(String totalPayFundAmount) {
        this.totalPayFundAmount = totalPayFundAmount;
        return this;
    }

    public void setTotalPayFundAmount(String totalPayFundAmount) {
        this.totalPayFundAmount = totalPayFundAmount;
    }

    public String getTransCurrency() {
        return transCurrency;
    }

    public AlipayFundAuthInfo transCurrency(String transCurrency) {
        this.transCurrency = transCurrency;
        return this;
    }

    public void setTransCurrency(String transCurrency) {
        this.transCurrency = transCurrency;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public AlipayFundAuthInfo userOrder(UserOrder userOrder) {
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
        if (!(o instanceof AlipayFundAuthInfo)) {
            return false;
        }
        return id != null && id.equals(((AlipayFundAuthInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AlipayFundAuthInfo{" +
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
            ", extraParam='" + getExtraParam() + "'" +
            ", fundAmount='" + getFundAmount() + "'" +
            ", gmtCreateStr='" + getGmtCreateStr() + "'" +
            ", gmtTransStr='" + getGmtTransStr() + "'" +
            ", operationId='" + getOperationId() + "'" +
            ", operationType='" + getOperationType() + "'" +
            ", orderTitle='" + getOrderTitle() + "'" +
            ", outOrderNo='" + getOutOrderNo() + "'" +
            ", outRequestNo='" + getOutRequestNo() + "'" +
            ", payerLogonId='" + getPayerLogonId() + "'" +
            ", payerUserId='" + getPayerUserId() + "'" +
            ", preAuthType='" + getPreAuthType() + "'" +
            ", remark='" + getRemark() + "'" +
            ", restAmount='" + getRestAmount() + "'" +
            ", restCreditAmount='" + getRestCreditAmount() + "'" +
            ", restFundAmount='" + getRestFundAmount() + "'" +
            ", status='" + getStatus() + "'" +
            ", totalFreezeAmount='" + getTotalFreezeAmount() + "'" +
            ", totalFreezeCreditAmount='" + getTotalFreezeCreditAmount() + "'" +
            ", totalFreezeFundAmount='" + getTotalFreezeFundAmount() + "'" +
            ", totalPayAmount='" + getTotalPayAmount() + "'" +
            ", totalPayCreditAmount='" + getTotalPayCreditAmount() + "'" +
            ", totalPayFundAmount='" + getTotalPayFundAmount() + "'" +
            ", transCurrency='" + getTransCurrency() + "'" +
            "}";
    }
}
