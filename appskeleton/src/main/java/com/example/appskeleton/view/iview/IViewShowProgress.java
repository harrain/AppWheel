package com.example.appskeleton.view.iview;

import android.content.Context;

/**
 * Created by yao on 2017/6/26.
 */

public interface IViewShowProgress {
    void showProgress(Context context, String msg,int flag);

    void hideProgress(Context context);
}
