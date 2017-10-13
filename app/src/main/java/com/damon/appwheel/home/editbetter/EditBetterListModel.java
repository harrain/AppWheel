package com.damon.appwheel.home.editbetter;

import com.damon.appwheel.api.EditBetterApi;
import com.damon.appwheel.utils.http.HttpMethods;
import com.damon.appwheel.utils.http.HttpResultFunc;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class EditBetterListModel {
  public static void getEditBetterList(ResourceSubscriber<List<EditBetter>> subscriber) {
    EditBetterApi mHotListApi =
        (EditBetterApi) HttpMethods.getInstance().setServise(EditBetterApi.class);
    mHotListApi.getEditBetterList()
        .map(new HttpResultFunc<List<EditBetter>>())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
  }
}
