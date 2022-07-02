package com.alisher.eshop.receiver;

import static com.alisher.eshop.utils.InternetUtils.isNetworkConnected;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alisher.eshop.utils.OnNetworkListener;

public class NetworkChangeReceiver extends BroadcastReceiver {

    OnNetworkListener onNetworkListener;

    public void setOnNetworkListener(OnNetworkListener onNetworkListener) {
        this.onNetworkListener = onNetworkListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isNetworkConnected(context)) {
            onNetworkListener.onNetworkDisconnected();
        } else {
            onNetworkListener.onNetworkConnected();
        }
    }
}
