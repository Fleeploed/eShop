package com.alisher.eshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alisher.eshop.model.apiResponse.FavoriteApiResponse;
import com.alisher.eshop.repository.FavoriteRepository;

public class FavoriteViewModel extends ViewModel {

    private final FavoriteRepository favoriteRepository;

    public FavoriteViewModel() {
        favoriteRepository = new FavoriteRepository();
    }

    public LiveData<FavoriteApiResponse> getFavorites(int userId) {
        return favoriteRepository.getFavorites(userId);
    }
}
