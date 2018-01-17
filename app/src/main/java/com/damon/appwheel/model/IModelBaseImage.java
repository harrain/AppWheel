package com.damon.appwheel.model;

import android.content.Context;
import android.widget.ImageView;


/**
 * 下载一组缩略图或头像的父接口
 */

public interface IModelBaseImage extends IModelBase {
    void showImage(Context context, String userName, ImageView imageView, int defaultPicId, boolean isDragging);

    void showImage(Context context, String userName, ImageView imageView, int width, int height, int defaultPicId, boolean isDragging);
}
