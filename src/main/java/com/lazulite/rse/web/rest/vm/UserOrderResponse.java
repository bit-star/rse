/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.lazulite.rse.web.rest.vm;

import com.alipay.api.response.AlipayTradeQueryResponse;


/**
 * @author alipay.demo
 *
 */
public class UserOrderResponse {

    private String cardId;

    private String goodsDetail;

    private AlipayTradeQueryResponse alipayTradeQueryResponse;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public AlipayTradeQueryResponse getAlipayTradeQueryResponse() {
        return alipayTradeQueryResponse;
    }

    public void setAlipayTradeQueryResponse(AlipayTradeQueryResponse alipayTradeQueryResponse) {
        this.alipayTradeQueryResponse = alipayTradeQueryResponse;
    }
}
