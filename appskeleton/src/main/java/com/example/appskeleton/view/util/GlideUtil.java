package com.example.appskeleton.view.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by data on 2017/9/28.
 */

public class GlideUtil {

    public static void showImage(Context context, @DrawableRes int resId, ImageView imageView){
        Glide.with(context).load(resId).into(imageView);
    }

    public static void showImage(Context context, @DrawableRes int resId, ImageView imageView,boolean isScrolling){
        if (!isScrolling)
        Glide.with(context).load(resId).into(imageView);
    }

    public static void showImage(Context context, String imgpath, ImageView imageView){
        Glide.with(context).load(imgpath).into(imageView);
    }

    public static void showImage(Context context, String imgpath, ImageView imageView,int width,int height){
        Glide.with(context).load(imgpath).override(width,height).into(imageView);
    }

    public static void showImage(Context context, File img, ImageView imageView, int width, int height){
        Glide.with(context).load(img).override(width,height).into(imageView);
    }

    public static void showImage(Context context, int imgId, ImageView imageView,int width,int height){
        Glide.with(context).load(imgId).override(width,height).into(imageView);
    }

    public static void showImage(Context context, String imgpath, ImageView imageView,boolean isScrolling){
        if (!isScrolling)
            Glide.with(context).load(imgpath).into(imageView);
    }

    public static void showImage(Context context, Uri uri, ImageView view, int width,int height) {
        Glide.with(context).load(uri).override(width,height).into(view);
    }
}
