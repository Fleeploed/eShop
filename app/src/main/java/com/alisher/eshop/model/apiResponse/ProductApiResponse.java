package com.alisher.eshop.model.apiResponse;

import com.alisher.eshop.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductApiResponse {

    @SerializedName("products")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
