package com.damon.appwheel;

import android.app.Application;
import android.content.Context;

import com.damon.appwheel.constant.ModelConstants;
import com.damon.appwheel.model.util.FileUtils;
import com.damon.appwheel.util.CrashUtils;
import com.damon.appwheel.util.ToastUtil;
import com.damon.appwheel.ui.util.Utils;
import com.damon.appwheel.util.LogUtils;
import com.damon.appwheel.util.ToastFactory;

/**
 * master分支为最新（MD风格），origin分支为之前写的版本
 */

public class App extends Application {


    public static App instance;
    public static int mainTid;
    public Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainTid = android.os.Process.myTid();
        mContext = getApplicationContext();

        Utils.init(mContext);
        CrashUtils.init(FileUtils.createFile(ModelConstants.CRASH_DIR));
        ToastUtil.init(mContext);
        LogUtils.init(this, BuildConfig.APPLICATION_ID, BuildConfig.DEBUG);
        ToastFactory.init(mContext,BuildConfig.DEBUG);

        //       LogUtils.init(this, BuildConfig.APPLICATION_ID, BuildConfig.DEBUG);
        //       ToastFactory.init(mContext,BuildConfig.DEBUG);
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static App getInstance() {
        return instance;
    }
}
