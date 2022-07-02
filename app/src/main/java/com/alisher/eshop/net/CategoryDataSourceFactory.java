package com.alisher.eshop.net;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.alisher.eshop.model.Product;

public class CategoryDataSourceFactory extends DataSource.Factory{

    // Creating the mutable live database
    private final MutableLiveData<PageKeyedDataSource<Integer, Product>> categoryLiveDataSource = new MutableLiveData<>();

    public static ProductDataSource categoryDataSource;

    private final String category;
    private final int userId;

    public CategoryDataSourceFactory(String category, int userId){
        this.category = category;
        this.userId = userId;
    }

    @Override
    public DataSource<Integer, Product> create() {
        // Getting our Data source object
        categoryDataSource = new ProductDataSource(category,userId);

        // Posting the Data source to get the values
        categoryLiveDataSource.postValue(categoryDataSource);

        // Returning the Data source
        return categoryDataSource;
    }
}