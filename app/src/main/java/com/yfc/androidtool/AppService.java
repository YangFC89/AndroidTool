package com.yfc.androidtool;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface AppService {

    /**
     * 商城首页
     *
     * @return
     */
    @POST("/homePage/selectHomePageData")
    Observable<HomeShopResponse> homeShop();
}
