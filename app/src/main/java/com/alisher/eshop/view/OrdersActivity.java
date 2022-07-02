package com.alisher.eshop.view;

import static com.alisher.eshop.utils.Constant.ORDER;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alisher.eshop.R;
import com.alisher.eshop.adapter.OrderAdapter;
import com.alisher.eshop.databinding.ActivityOrdersBinding;
import com.alisher.eshop.model.Order;
import com.alisher.eshop.storage.LoginUtils;
import com.alisher.eshop.viewmodel.OrderViewModel;

public class OrdersActivity extends AppCompatActivity implements OrderAdapter.OrderAdapterOnClickHandler {

    private ActivityOrdersBinding binding;
    private OrderViewModel orderViewModel;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_orders);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.orders));
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        setUpRecycleView();

        getOrders();
    }

    private void setUpRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.orderList.setLayoutManager(layoutManager);
        binding.orderList.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.orderList.addItemDecoration(dividerItemDecoration);
    }

    private void getOrders() {
        orderViewModel.getOrders(LoginUtils.getInstance(this).getUserInfo().getId()).observe(this, orderApiResponse -> {
            orderAdapter = new OrderAdapter( orderApiResponse.getOrderList(),this);
            binding.orderList.setAdapter(orderAdapter);
        });
    }

    @Override
    public void onClick(Order order) {
        Intent intent = new Intent(OrdersActivity.this, StatusActivity.class);

        intent.putExtra(ORDER, (order));
        startActivity(intent);
    }
}
