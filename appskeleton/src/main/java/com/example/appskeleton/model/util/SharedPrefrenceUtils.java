package com.example.appskeleton.model.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appskeleton.view.util.Utils;


/**
 * Created by clawpo on 2017/3/21.
 */

public class SharedPrefrenceUtils {
    private static final String PREFRENCE_NAME = "APP";
    private static final String CAMERA_TURN = "cameraId";
    private static final String NEED_LOCATE = "need_locate";
    private static final String LOCATE_INTERRUPT = "locate_interrupt";
    private static final String APP_WHITELIST = "app_whiteList";
    private static final String RECENT_TRACE_PATH = "recent_trace_path";
    private static final String ISLOGIN = "isLogin";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CURRENT_TIME = "current_time";
    private static final String millisUntilFinished = "millisUntilFinished";
    private static final String HAS_CRASH_LOG_UNUPLOAD = "has_crash_log_unupload";
    private static final String DRIVER_ID = "driver_id";
    private static final String REMEMBER_PASSWORD = "remember_password";

    private static SharedPrefrenceUtils instance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public SharedPrefrenceUtils() {
        /*sharedPreferences = FuLiCenterApplication.getInstance().
                getSharedPreferences(SHARE_PREFRENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();*/
    }

    public static SharedPrefrenceUtils getInstance(){
        if (instance==null){
            instance = new SharedPrefrenceUtils();
            sharedPreferences = Utils.getContext().
                    getSharedPreferences(PREFRENCE_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return instance;
    }

    public void setCameraTurn(int cameraId){
        editor.putInt(CAMERA_TURN,cameraId).commit();
    }
    public int getCameraTurn(){
        return sharedPreferences.getInt(CAMERA_TURN,1);
    }

    public void setNeedLocate(boolean needLocate){ editor.putBoolean(NEED_LOCATE,needLocate).commit();}
    public boolean getNeedLocate(){ return sharedPreferences.getBoolean(NEED_LOCATE,false);}

    public void setLocateInterrupt(boolean isInterrupt){ editor.putBoolean(LOCATE_INTERRUPT,isInterrupt).commit();}
    public boolean getLocateInterrupt(){ return sharedPreferences.getBoolean(LOCATE_INTERRUPT,false);}

    public void setAddWhiteList(boolean isAdded){ editor.putBoolean(APP_WHITELIST,isAdded).commit();}
    public boolean getAddWhiteList() { return sharedPreferences.getBoolean(APP_WHITELIST,false);}

    public void removeCameraTurn(){
        editor.remove(CAMERA_TURN).commit();
    }

    public void setRecentTracePath(String path){ editor.putString(RECENT_TRACE_PATH,path).commit();}

    public String getRecentTraceFilePath() {
        return sharedPreferences.getString(RECENT_TRACE_PATH,null);
    }

    public void setLoginState(boolean isLogin){ editor.putBoolean(ISLOGIN,isLogin).commit();}

    public boolean isLogin() {
        return sharedPreferences.getBoolean(ISLOGIN,false);
    }

    public void removeLoginState(){
        editor.remove(ISLOGIN);
    }

    public void setUsername(String username){ editor.putString(USERNAME,username).commit();}

    public String getUsername() {
        return sharedPreferences.getString(USERNAME,null);
    }

    public void removeUsername(){
        editor.remove(USERNAME);
    }

    public void setPassword(String password){ editor.putString(PASSWORD,password).commit();}

    public String getPassword() {
        return sharedPreferences.getString(PASSWORD,null);
    }

    public void removePassword(){
        editor.remove(PASSWORD);
    }

    public void setRememberPassword(boolean rememberPassword){
        editor.putBoolean(REMEMBER_PASSWORD,rememberPassword).commit();
    }

    public boolean isRememberPassword(){
        return sharedPreferences.getBoolean(REMEMBER_PASSWORD,false);
    }

    public void setDriverId(String driverid){ editor.putString(DRIVER_ID,driverid).commit();}

    public String getDriverId() {
        return sharedPreferences.getString(DRIVER_ID,null);
    }

    public void removeDriverId(){
        editor.remove(DRIVER_ID);
    }

    public  String getCurrentTime() {
        return sharedPreferences.getString(CURRENT_TIME,null);
    }

    public  void setCurrentTime(String time){
        editor.putString(CURRENT_TIME,time).commit();
    }

    public void setMillisUntilFinished(long millis){
        editor.putLong(millisUntilFinished,millis).commit();
    }

    public long getMillisUntilFinished(){
        return sharedPreferences.getLong(millisUntilFinished,0);
    }

    public void setHasCrashLogUnupload(boolean hasCrashLogUnupload){
        editor.putBoolean(HAS_CRASH_LOG_UNUPLOAD,hasCrashLogUnupload).commit();
    }

    public boolean getHasCrashLogUnupload(){
        return sharedPreferences.getBoolean(HAS_CRASH_LOG_UNUPLOAD,false);
    }

    public void removeHasCrashLogUnupload(){
        editor.remove(HAS_CRASH_LOG_UNUPLOAD);
    }

    
}
