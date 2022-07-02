package com.alisher.eshop.viewmodel;

import static com.alisher.eshop.net.CategoryDataSourceFactory.categoryDataSource;
import static com.alisher.eshop.net.ProductDataSourceFactory.productDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.alisher.eshop.model.Product;
import com.alisher.eshop.net.CategoryDataSourceFactory;
import com.alisher.eshop.net.ProductDataSource;
import com.alisher.eshop.net.ProductDataSourceFactory;

public class ProductViewModel extends ViewModel {

    // Create liveData for PagedList and PagedKeyedDataSource
    public LiveData<PagedList<Product>> productPagedList;

    public LiveData<PagedList<Product>> cosmeticPagedList;

    // Get PagedList configuration
    private static final PagedList.Config  pagedListConfig =
            (new PagedList.Config.Builder())
                    .setEnablePlaceholders(false)
                    .setPageSize(ProductDataSource.PAGE_SIZE)
                    .build();

    public void loadClothes(String category, int userId){
        // Get our database source factory
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(category,userId);

        // Build the paged list
        productPagedList = (new LivePagedListBuilder<>(productDataSourceFactory, pagedListConfig)).build();
    }

    public void loadCosmetics(String category, int userId){
        // Get our database source factory
        CategoryDataSourceFactory categoryDataSourceFactory = new CategoryDataSourceFactory(category,userId);

        // Build the paged list
        cosmeticPagedList = (new LivePagedListBuilder<>(categoryDataSourceFactory, pagedListConfig)).build();
    }

    public void invalidate(){
        if(productDataSource != null) productDataSource.invalidate();
        if(categoryDataSource != null) categoryDataSource.invalidate();
    }
}
