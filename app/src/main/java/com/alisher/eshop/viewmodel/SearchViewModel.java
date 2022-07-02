package com.alisher.eshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alisher.eshop.model.apiResponse.ProductApiResponse;
import com.alisher.eshop.repository.SearchRepository;

public class SearchViewModel  extends ViewModel {

    private final SearchRepository searchRepository;

    public SearchViewModel(  ) {
        searchRepository = new SearchRepository();
    }


    public LiveData<ProductApiResponse> getProductsBySearch(String keyword, int userId) {
        return searchRepository.getResponseDataBySearch(keyword, userId);
    }
}
