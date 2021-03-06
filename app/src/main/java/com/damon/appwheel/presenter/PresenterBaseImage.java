package com.damon.appwheel.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.damon.appwheel.model.ModelBaseImage;
import com.damon.appwheel.ui.util.Utils;


/**
 * Created by yao on 2017/6/26.
 */

public class PresenterBaseImage extends PresenterBase implements IPresenterBaseImage {
    ModelBaseImage mModel;

    public PresenterBaseImage() {
        super(Utils.getContext());
        mModel=new ModelBaseImage();
    }

    @Override
    public void release() {
        mModel.release();
    }

    @Override
    public void showImage(Context context, String userName, ImageView imageView, int defaultPicId, boolean isDragging) {
        mModel.showImage(context,userName,imageView,defaultPicId,isDragging);
    }

    @Override
    public void showImage(Context context, String userName, ImageView imageView, int width, int height, int defaultPicId, boolean isDragging) {
        mModel.showImage(context,userName,imageView,width,height,defaultPicId,isDragging);
    }
}
