package com.damon.appwheel.model;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by yao on 2017/6/26.
 */

public class ModelBaseImage extends ModelBase implements IModelBaseImage {

    @Override
    public void release() {
        super.release();
//        OkImageLoader.release();
    }

    @Override
    public void showImage(Context context, String userName, ImageView imageView, int defaultPicId, boolean isDragging) {
//        OkImageLoader.build(I.DOWNLOAD_AVATAR_URL+userName)
//                .imageView(imageView)
//                .setDragging(isDragging)
//                .showImage(context);
    }

    @Override
    public void showImage(Context context, String userName, ImageView imageView, int width, int height, int defaultPicId, boolean isDragging) {
//        OkImageLoader.build(I.DOWNLOAD_AVATAR_URL+userName)
//                .width(width)
//                .height(height)
//                .imageView(imageView)
//                .setDragging(isDragging)
//                .showImage(context);
    }
}
