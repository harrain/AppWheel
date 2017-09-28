package com.example.appskeleton.view.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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

    public static void showImage(Context context, String imgpath, ImageView imageView,boolean isScrolling){
        if (!isScrolling)
            Glide.with(context).load(imgpath).into(imageView);
    }
}
