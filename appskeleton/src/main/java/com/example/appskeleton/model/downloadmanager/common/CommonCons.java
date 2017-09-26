package com.example.appskeleton.model.downloadmanager.common;

import android.os.Environment;
import android.os.SystemClock;

import com.example.appskeleton.AppConstants;

import java.io.File;

/**
 * Created by maimingliang on 2016/10/8.
 */

public class CommonCons {


    public final static String SAVE_APP_NAME = AppConstants.APPLICATION_ID+SystemClock.elapsedRealtime()+".apk";

    public final static String SAVE_APP_LOCATION = Environment.DIRECTORY_DOWNLOADS;


    public final static String APP_FILE_NAME = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+ File.separator + SAVE_APP_NAME;
    public static final String DOWNLOAD_APK_ID_PREFS = "download_apk_id_prefs";
}
