/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.lazulite.rse.web.rest.vm;

import java.util.List;

/**
 * @author alipay.demo
 *
 */
public class UserOrderListResponse extends SuccessResponse {

    private List<UserOrderResponse> userOrderResponseList;

    public List<UserOrderResponse> getUserOrderResponseList() {
        return userOrderResponseList;
    }

    public void setUserOrderResponseList(List<UserOrderResponse> userOrderResponseList) {
        this.userOrderResponseList = userOrderResponseList;
    }

}
