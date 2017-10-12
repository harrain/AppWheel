package com.example.appskeleton.constant;

/**
 * Created by data on 2017/9/28.
 */

public class Url {

    //请求apk升级更新的地址 apk安装包下载地址
    public static final String appUpdateUrl = "http://10.0.100.221/hy/delivery/Upload/apk/delivery.apk";
    //判断服务器是否有App新版本存在 地址
    public static final String checkUpdateUrl = "http://10.0.100.221/hy/delivery/index.php/Index/version";
    public static String uploadCrashLogUrl = "http://10.0.100.221/hy/delivery/index.php/Public/_upload" ;
    public static final String ACTION = "action";
    public static final String ACTION_LOGIN = "login-login";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
}
