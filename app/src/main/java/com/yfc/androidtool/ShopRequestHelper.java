package com.yfc.androidtool;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yfc.androidu.RxExtension;

import io.reactivex.Observable;

public class ShopRequestHelper {

    private static AppService getShopService(RxAppCompatActivity activity) {
        return AppConnection.getClient(activity).create(AppService.class);
    }


    /**
     * 商城首页
     */
    public static Observable<HomeShopResponse> serverHomeShop(RxAppCompatActivity activity) {
        return getShopService(activity).homeShop().compose(RxExtension.applys(activity));
    }

}
