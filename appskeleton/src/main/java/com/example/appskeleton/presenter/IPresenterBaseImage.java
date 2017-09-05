package com.example.appskeleton.presenter;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by yao on 2017/6/26.
 */

public interface IPresenterBaseImage extends IPresenterBase {
    void showImage(Context context, String userName, ImageView imageView, int defaultPicId, boolean isDragging);

    void showImage(Context context, String userName, ImageView imageView, int width, int height, int defaultPicId, boolean isDragging);

}
