package com.yfc.androidtool;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


public class MainActivity extends RxAppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShopRequestHelper.serverHomeShop(MainActivity.this)
                .subscribe(homeShopResponse -> {
                    Log.i("Okhttp", homeShopResponse.message);
                }, throwable -> throwable.printStackTrace());

    }
}
