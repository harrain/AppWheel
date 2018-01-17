package com.damon.appwheel.ui.util;

import android.content.Context;


/**
 * Created by data on 2017/9/1.
 */

public class Utils {

    private static Context mContext;

    public static void init(Context context){mContext = context;}

    public static Context getContext(){
        return mContext;
    }
}
