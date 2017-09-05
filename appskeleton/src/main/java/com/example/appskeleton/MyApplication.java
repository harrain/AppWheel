package com.example.appskeleton;

import android.app.Application;

/**
 * Created by data on 2017/9/1.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private static int mainTid;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainTid = android.os.Process.myTid();

//        LogUtils.init(this,BuildConfig.APPLICATION_ID,BuildConfig.DEBUG);
//        CrashUtils.init(FileUtils.createFile(ModelConstants.CRASH_DIR));
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
