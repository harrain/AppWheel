package com.damon.appwheel.ui.home.hotlist;

import com.damon.appwheel.api.HotListApi;
import com.damon.appwheel.utils.http.HttpMethods;
import com.damon.appwheel.utils.http.HttpResultFunc;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class HotListModel {
  public static void getHotList(ResourceSubscriber<List<HotThing>> subscriber) {
    HotListApi mHotListApi = (HotListApi) HttpMethods.getInstance().setServise(HotListApi.class);
    mHotListApi.getHotList()
        .map(new HttpResultFunc<List<HotThing>>())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
  }
}
