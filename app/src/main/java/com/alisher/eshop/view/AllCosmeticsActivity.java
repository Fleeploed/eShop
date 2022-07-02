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
import com.alisher.eshop.databinding.ActivityAllCosmeticsBinding;
import com.alisher.eshop.model.Product;
import com.alisher.eshop.storage.LoginUtils;
import com.alisher.eshop.viewmodel.ProductViewModel;

public class AllCosmeticsActivity extends AppCompatActivity implements ProductAdapter.ProductAdapterOnClickHandler {

    private ActivityAllCosmeticsBinding binding;
    private ProductAdapter cosmeticAdapter;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_cosmetics);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.all_cosmetics));

        int userID = LoginUtils.getInstance(this).getUserInfo().getId();

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.loadCosmetics("cosmetic",userID);

        setupRecyclerViews();

        getAllCosmetics();
    }

    private void setupRecyclerViews() {
        // Cosmetics
        binding.allCosmeticsRecyclerView.setHasFixedSize(true);
        binding.allCosmeticsRecyclerView.setLayoutManager(new GridLayoutManager(this, (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 2 : 4));
        cosmeticAdapter = new ProductAdapter(this, this);
    }

    public void getAllCosmetics() {
        productViewModel.cosmeticPagedList.observe(this, products -> cosmeticAdapter.submitList(products));
        binding.allCosmeticsRecyclerView.setAdapter(cosmeticAdapter);
    }

    @Override
    public void onClick(Product product) {
        Intent intent = new Intent(AllCosmeticsActivity.this, DetailsActivity.class);
        intent.putExtra(PRODUCT, (product));
        startActivity(intent);
    }
}
