package com.example.appskeleton;

import android.app.Application;
import android.content.Context;

import com.example.appskeleton.model.ModelConstants;
import com.example.appskeleton.model.util.FileUtils;
import com.example.appskeleton.view.util.Utils;

/**
 * Created by data on 2017/9/1.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private static int mainTid;
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainTid = android.os.Process.myTid();
        mContext = getApplicationContext();
        LogUtils.init(this,AppConstants.APPLICATION_ID,BuildConfig.DEBUG);
        CrashUtils.init(FileUtils.createFile(ModelConstants.CRASH_DIR));
        Utils.init(mContext);
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
