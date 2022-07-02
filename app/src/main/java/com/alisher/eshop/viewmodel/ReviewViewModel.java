package com.alisher.eshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alisher.eshop.model.apiResponse.ReviewApiResponse;
import com.alisher.eshop.repository.ReviewRepository;

public class ReviewViewModel extends ViewModel {

    private final ReviewRepository reviewRepository;

    public ReviewViewModel(  ) {
        reviewRepository = new ReviewRepository();
    }

    public LiveData<ReviewApiResponse> getReviews(int productId) {
        return reviewRepository.getReviews(productId);
    }
}
