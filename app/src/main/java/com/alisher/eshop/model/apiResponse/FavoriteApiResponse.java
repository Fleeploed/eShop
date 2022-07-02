package com.alisher.eshop.model.apiResponse;

import com.alisher.eshop.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteApiResponse {

    @SerializedName("favorites")
    private List<Product> favorites;

    public List<Product> getFavorites() {
        return favorites;
    }
}
