package com.damon.appwheel.view.iview;

import android.content.Context;

/**
 * Created by yao on 2017/6/26.
 */

public interface IViewShowProgress {
    void showProgress(Context context, String msg);

    void hideProgress(Context context);
}
