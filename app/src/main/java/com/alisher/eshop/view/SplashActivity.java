package com.alisher.eshop.view;

import static com.alisher.eshop.storage.LanguageUtils.loadLocale;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.alisher.eshop.R;
import com.alisher.eshop.storage.LoginUtils;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, ProductActivity.class);
            startActivity(i);

            // Zavrit tuto aktivitu
            finish();
            // Pokud se uzivatel drive neprihlasi, prejde na LoginActivity
            if(!LoginUtils.getInstance(SplashActivity.this).isLoggedIn()) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        }, SPLASH_TIME_OUT);
    }
}
