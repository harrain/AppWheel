package com.damon.appwheel.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.damon.appwheel.R;
import com.damon.appwheel.util.NetworkUtils;

import java.lang.reflect.Field;
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

    public <T extends View> T getView(@IdRes int viewId){
        return (T) findViewById(viewId);
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

    public boolean checkNetStatus(View view){
//        if (NetworkUtils.getDataEnabled() || NetworkUtils.getWifiEnabled()) return true;
        if ( NetworkUtils.isConnected()) return true;
        Snackbar.make(view, R.string.net_internal, Snackbar.LENGTH_INDEFINITE)
                .setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
        return false;
    }

    public void checkNetStatus(View view,String text){
        if ( NetworkUtils.getDataEnabled() || NetworkUtils.getWifiEnabled()) return;
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
                .setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    public void checkNetStatus(View view,@StringRes int id){
        if ( NetworkUtils.getDataEnabled() || NetworkUtils.getWifiEnabled()) return;
        Snackbar.make(view, id, Snackbar.LENGTH_INDEFINITE)
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        fixInputMethodManagerLeak(this);
//		if(receiver!=null){
//			unregisterReceiver(receiver);
//			receiver=null;
//		}
    }

    public void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String [] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0;i < arr.length;i ++) {
            String param = arr[i];
            try{
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了

                            Log.i(tag, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);

                        break;
                    }
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

}
