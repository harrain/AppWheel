package com.damon.appwheel.api;

import com.damon.appwheel.home.editbetter.EditBetter;
import com.damon.appwheel.home.hotlist.HotThing;
import com.damon.appwheel.home.limitedvolume.LimitedVolume;
import com.damon.appwheel.utils.HttpsResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public interface HomeApi {
  @GET("taglabang?ver=2&platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang&count=20")
  Flowable<HttpsResult<List<HotThing>>> getHotList();

  @GET("timecoupon?platform=lapinapp_android&imagetype=1&platform=lapinapp_android&channel=kushichang")
  Flowable<HttpsResult<List<LimitedVolume>>> getLimitedVolumeList();

  @GET("indexlistsegbyid?count=20&platform=lapinapp_android&channel=yingyongbao&imagetype=1")
  Flowable<HttpsResult<List<EditBetter>>> getEditBetterList();
}
