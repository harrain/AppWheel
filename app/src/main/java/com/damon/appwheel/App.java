package com.damon.appwheel;

import com.example.appskeleton.MyApplication;
import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.util.ToastFactory;

/**
 * origin为之前写的版本
 */

public class App extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(this, BuildConfig.APPLICATION_ID, BuildConfig.DEBUG);
        ToastFactory.init(mContext,BuildConfig.DEBUG);
    }
}
