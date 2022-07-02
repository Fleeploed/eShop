package com.alisher.eshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alisher.eshop.model.Ordering;
import com.alisher.eshop.repository.OrderingRepository;

import okhttp3.ResponseBody;

public class OrderingViewModel extends ViewModel {

    private final OrderingRepository orderingRepository;

    public OrderingViewModel(  ) {
        orderingRepository = new OrderingRepository();
    }

    public LiveData<ResponseBody> orderProduct(Ordering ordering) {
        return orderingRepository.orderProduct(ordering);
    }
}
