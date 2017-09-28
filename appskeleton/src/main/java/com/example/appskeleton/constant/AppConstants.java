package com.example.appskeleton.constant;

import android.os.Environment;

/**
 * Created by data on 2017/9/4.
 */

public class AppConstants {

    public static final String APPLICATION_ID = "com.damon.appwheel";


    public static final String  TRACE_TXT_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/" + "trace.txt";
    //    public static final String  TRACES_DIR = mContext
//            .getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/" + "traces";
    public static final String  CRASH_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/" + "crashes";
    public static final String  TRACES_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()+"/traces";
    public static final String CAMERA_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()+"/Camera/";
    public static final String GALARY_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()+"/";
    public static final String LOCATION_BROADCAST = "com.example.demowechat.map.latlng";



}
