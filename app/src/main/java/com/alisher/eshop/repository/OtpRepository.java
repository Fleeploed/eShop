package com.alisher.eshop.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alisher.eshop.model.Otp;
import com.alisher.eshop.net.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpRepository {

    private static final String TAG = OtpRepository.class.getSimpleName();

    public LiveData<Otp> getOtpCode(String token , String email) {
        final MutableLiveData<Otp> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getInstance().getApi().getOtp(token,email).enqueue(new Callback<Otp>() {
            @Override
            public void onResponse(@NonNull Call<Otp> call, @NonNull Response<Otp> response) {
                Otp otp;
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: Succeeded");
                    otp = response.body();
                } else {
                    Log.d(TAG, "onResponse: Incorrect Email");
                    otp = new Otp("Incorrect Email");
                }
                mutableLiveData.setValue(otp);

            }

            @Override
            public void onFailure(@NonNull Call<Otp> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
