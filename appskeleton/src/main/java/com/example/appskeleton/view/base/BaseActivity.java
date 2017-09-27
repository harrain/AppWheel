package com.example.appskeleton.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.appskeleton.R;
import com.example.appskeleton.util.NetworkUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by stephen on 2017/7/25.
 */

public  class BaseActivity extends AppCompatActivity {

    // 管理运行的所有的activity
    public final static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
    public static BaseActivity activity;

//	private KillAllReceiver receiver;
//	private class KillAllReceiver extends BroadcastReceiver{
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			finish();
//		}
//	}

    public Context mContext;
    public boolean first;
    public String tag;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		receiver=new KillAllReceiver();
//		IntentFilter filter=new IntentFilter("com.app.killall");
//		registerReceiver(receiver, filter);
        mContext = this;
        tag = getLocalClassName();

        synchronized (mActivities) {
            mActivities.add(this);
        }
        init();
        initView();
        notifyData();

    }

    @Override
    public void onResume() {
        super.onResume();
        activity = this;

    }

    @Override
    public void onPause() {
        super.onPause();
        activity = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
//		if(receiver!=null){
//			unregisterReceiver(receiver);
//			receiver=null;
//		}
    }

    public static void killAll() {
        // 复制了一份mActivities 集合
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public  void initView(){}

    public void init() {}

    public void notifyData(){}

    public void checkNetStatus(View view){
        if ( NetworkUtils.getDataEnabled() || NetworkUtils.getWifiEnabled()) return;
        Snackbar.make(view, R.string.net_internal, Snackbar.LENGTH_INDEFINITE)
                .setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBack();
    }

    public void onBack() {
        finish();
    }
}
