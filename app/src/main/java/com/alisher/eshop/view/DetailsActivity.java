package com.alisher.eshop.view;

import static com.alisher.eshop.storage.LanguageUtils.loadLocale;
import static com.alisher.eshop.utils.Constant.LOCALHOST;
import static com.alisher.eshop.utils.Constant.PRODUCT;
import static com.alisher.eshop.utils.Constant.PRODUCTID;
import static com.alisher.eshop.utils.Constant.PRODUCT_ID;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.alisher.eshop.R;
import com.alisher.eshop.adapter.ReviewAdapter;
import com.alisher.eshop.databinding.ActivityDetailsBinding;
import com.alisher.eshop.model.Cart;
import com.alisher.eshop.model.Product;
import com.alisher.eshop.model.Review;
import com.alisher.eshop.storage.LoginUtils;
import com.alisher.eshop.utils.RequestCallback;
import com.alisher.eshop.viewmodel.ReviewViewModel;
import com.alisher.eshop.viewmodel.ToCartViewModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DetailsActivity";

    private ActivityDetailsBinding binding;
    private ReviewViewModel reviewViewModel;
    private ToCartViewModel toCartViewModel;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);
        toCartViewModel = ViewModelProviders.of(this).get(ToCartViewModel.class);

        binding.txtSeeAllReviews.setOnClickListener(this);
        binding.writeReview.setOnClickListener(this);
        binding.addToCart.setOnClickListener(this);
        binding.buy.setOnClickListener(this);

        getProductDetails();

        setUpRecycleView();

        getReviewsOfProduct();
    }

    private void setUpRecycleView() {
        binding.listOfReviews.setHasFixedSize(true);
        binding.listOfReviews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.listOfReviews.setItemAnimator(null);
    }

    private void getProductDetails() {
        // Prijmout objekt produktu
        product = getIntent().getParcelableExtra(PRODUCT);

        Log.d(TAG,"isFavourite " + product.isFavourite() + " isInCart " + product.isInCart());

        // Nastavit layout ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_title_layout);
        ((TextView) findViewById(R.id.action_bar_title)).setText(product.getProductName());

        binding.nameOfProduct.setText(product.getProductName());
        binding.detailOfProduct.setText(product.getProductDetail());
        binding.priceOfProduct.setText(product.getProductPrice() + " CZK");

        String imageUrl = LOCALHOST + product.getProductImage().replaceAll("\\\\", "/");
        Glide.with(this)
                .load(imageUrl)
                .into(binding.imageOfProduct);
    }

    private void getReviewsOfProduct() {
        reviewViewModel.getReviews(product.getProductId()).observe(this, reviewApiResponse -> {
            if (reviewApiResponse != null) {
                reviewList = reviewApiResponse.getReviewList();
                reviewAdapter = new ReviewAdapter(reviewList);
                binding.listOfReviews.setAdapter(reviewAdapter);
            }

            if(reviewList.size() == 0){
                binding.listOfReviews.setVisibility(View.GONE);
                binding.txtFirst.setVisibility(View.VISIBLE);
            }else {
                binding.listOfReviews.setVisibility(View.VISIBLE);
                binding.txtFirst.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtSeeAllReviews) {
            Intent allReviewIntent = new Intent(DetailsActivity.this, AllReviewsActivity.class);
            allReviewIntent.putExtra(PRODUCT_ID,product.getProductId());
            startActivity(allReviewIntent);
        } else if (view.getId() == R.id.writeReview) {
            Intent allReviewIntent = new Intent(DetailsActivity.this, WriteReviewActivity.class);
            allReviewIntent.putExtra(PRODUCT_ID,product.getProductId());
            startActivity(allReviewIntent);
        }else if(view.getId() == R.id.addToCart){
            insertToCart(() -> product.setIsInCart(true));
            Intent cartIntent = new Intent(DetailsActivity.this, CartActivity.class);
            startActivity(cartIntent);
        }else if(view.getId() == R.id.buy){
            Intent shippingIntent = new Intent(DetailsActivity.this, ShippingAddressActivity.class);
            shippingIntent.putExtra(PRODUCTID, product.getProductId());
            startActivity(shippingIntent);
        }
    }

    private void insertToCart(RequestCallback callback) {
        Cart cart = new Cart(LoginUtils.getInstance(this).getUserInfo().getId(), product.getProductId());
        toCartViewModel.addToCart(cart, callback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getReviewsOfProduct();
    }
}