package com.alisher.eshop.model.apiResponse;

import com.alisher.eshop.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryApiResponse {

    @SerializedName("history")
    private List<Product> historyList;

    public List<Product> getHistoryList() {
        return historyList;
    }
}
