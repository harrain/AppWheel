package com.damon.appwheel.api;

import com.damon.appwheel.ui.home.limitedvolume.LimitedVolume;
import com.damon.appwheel.utils.HttpsResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface LimitedVolumeApi {
  @GET("timecoupon?platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang")
  Flowable<HttpsResult<List<LimitedVolume>>> getLimitedVolumeList();
}