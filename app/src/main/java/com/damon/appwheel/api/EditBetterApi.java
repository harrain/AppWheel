package com.damon.appwheel.api;

import com.damon.appwheel.ui.home.editbetter.EditBetter;
import com.damon.appwheel.utils.HttpsResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface EditBetterApi {
  @GET("indexlistsegbyid?count=20&platform=lapinapp_android&channel=yingyongbao&imagetype=1")
  Flowable<HttpsResult<List<EditBetter>>> getEditBetterList();
}