package com.alisher.eshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alisher.eshop.model.Favorite;
import com.alisher.eshop.repository.AddFavoriteRepository;
import com.alisher.eshop.utils.RequestCallback;

import okhttp3.ResponseBody;

public class AddFavoriteViewModel extends ViewModel {

    private final AddFavoriteRepository addFavoriteRepository;

    public AddFavoriteViewModel() {
        addFavoriteRepository = new AddFavoriteRepository();
    }

    public LiveData<ResponseBody> addFavorite(Favorite favorite, RequestCallback callback) {
        return addFavoriteRepository.addFavorite(favorite,callback);
    }
}
