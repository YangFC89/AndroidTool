package com.yfc.androidtool;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface AppService {

    @POST("/homePage/selectHomePageData")
    Observable<HomeShopResponse> homeShop();
}
