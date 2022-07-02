package com.alisher.eshop.view;

import static com.alisher.eshop.utils.Constant.KEYWORD;
import static com.alisher.eshop.utils.Constant.PRODUCT;
import static com.alisher.eshop.utils.InternetUtils.isNetworkConnected;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alisher.eshop.R;
import com.alisher.eshop.adapter.SearchAdapter;
import com.alisher.eshop.databinding.ActivityResultBinding;
import com.alisher.eshop.model.Product;
import com.alisher.eshop.storage.LoginUtils;
import com.alisher.eshop.viewmodel.SearchViewModel;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    private SearchAdapter searchAdapter;
    private List<Product> searchedList;
    private SearchViewModel searchViewModel;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra(KEYWORD);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(keyword);

        userId = LoginUtils.getInstance(this).getUserInfo().getId();

        if (isNetworkConnected(getApplicationContext())) {
            search(keyword);
        }
    }

    private void search(String query) {

        binding.listOfSearchedList.setHasFixedSize(true);
        binding.listOfSearchedList.setLayoutManager(new GridLayoutManager(this, (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 2 : 4));

        searchViewModel.getProductsBySearch(query, userId).observe(this, productApiResponse -> {
            if (productApiResponse != null) {
                searchedList = productApiResponse.getProducts();
                if (searchedList.isEmpty()) {
                    Toast.makeText(ResultActivity.this, "Žádný výsledek", Toast.LENGTH_SHORT).show();
                }

                searchAdapter = new SearchAdapter(getApplicationContext(), searchedList, product -> {
                    Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);
                    intent.putExtra(PRODUCT, product);
                    startActivity(intent);
                },this);
            }
            binding.listOfSearchedList.setAdapter(searchAdapter);
        });
    }
}
