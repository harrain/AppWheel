package com.example.appskeleton;

import android.app.Application;
import android.content.Context;

import com.example.appskeleton.model.ModelConstants;
import com.example.appskeleton.model.util.FileUtils;
import com.example.appskeleton.util.CrashUtils;
import com.example.appskeleton.util.ToastUtil;
import com.example.appskeleton.view.util.Utils;

/**
 * Created by data on 2017/9/1.
 */

public class MyApplication extends Application {

    public static MyApplication instance;
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

 //       LogUtils.init(this, BuildConfig.APPLICATION_ID, BuildConfig.DEBUG);
 //       ToastFactory.init(mContext,BuildConfig.DEBUG);
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
