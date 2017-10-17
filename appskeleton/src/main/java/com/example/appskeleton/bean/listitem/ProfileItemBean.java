package com.example.appskeleton.bean.listitem;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by data on 2017/10/16.
 */

public class ProfileItemBean {

    private String text;
    private String des;
    private String imgPath;
    private int resId;
    public int itemType = -1;
    private Intent intent;
    private boolean isStartIntent;
    private Uri uri;

    public ProfileItemBean(String text, String des, String imgPath, int resId,int itemType) {
        this.text = text;
        this.des = des;
        this.imgPath = imgPath;
        this.resId = resId;
        this.itemType = itemType;
    }

    public ProfileItemBean(String text, String des, String imgPath, int resId, int itemType, Intent intent, boolean isStartIntent) {
        this.text = text;
        this.des = des;
        this.imgPath = imgPath;
        this.resId = resId;
        this.itemType = itemType;
        this.intent = intent;
        this.isStartIntent = isStartIntent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public boolean isStartIntent() {
        return isStartIntent;
    }

    public void setStartIntent(boolean startIntent) {
        isStartIntent = startIntent;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
