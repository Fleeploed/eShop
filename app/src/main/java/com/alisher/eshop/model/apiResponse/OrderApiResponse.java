package com.alisher.eshop.model.apiResponse;

import com.alisher.eshop.model.Order;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderApiResponse {

    @SerializedName("orders")
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }
}
