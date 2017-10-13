package com.damon.appwheel.api;

import com.damon.appwheel.home.hotlist.HotThing;
import com.damon.appwheel.utils.HttpsResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface HotListApi {
  @GET("taglabang?ver=2&platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang&count=20")
  Flowable<HttpsResult<List<HotThing>>> getHotList();
}