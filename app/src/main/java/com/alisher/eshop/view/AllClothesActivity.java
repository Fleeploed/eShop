package com.alisher.eshop.view;

import static com.alisher.eshop.storage.LanguageUtils.loadLocale;
import static com.alisher.eshop.utils.Constant.PRODUCT;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alisher.eshop.R;
import com.alisher.eshop.adapter.ProductAdapter;
import com.alisher.eshop.databinding.ActivityAllClothesBinding;
import com.alisher.eshop.model.Product;
import com.alisher.eshop.storage.LoginUtils;
import com.alisher.eshop.viewmodel.ProductViewModel;

public class AllClothesActivity extends AppCompatActivity implements ProductAdapter.ProductAdapterOnClickHandler{

    private ActivityAllClothesBinding binding;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_clothes);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.all_clothes_1));

        int userID = LoginUtils.getInstance(this).getUserInfo().getId();

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.loadClothes("clothes", userID);

        setupRecyclerViews();

        getAllClothes();
    }

    private void setupRecyclerViews() {
        // Clothes
        binding.allClothesRecyclerView.setLayoutManager(new GridLayoutManager(this, (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 2 : 4));
        binding.allClothesRecyclerView.setHasFixedSize(true);
        productAdapter = new ProductAdapter(this,this);
    }

    public void getAllClothes() {
        productViewModel.productPagedList.observe(this, products -> {
            productAdapter.submitList(products);
        });

        binding.allClothesRecyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onClick(Product product) {
        Intent intent = new Intent(AllClothesActivity.this, DetailsActivity.class);
        intent.putExtra(PRODUCT, (product));
        startActivity(intent);
    }
}
