package com.example.appskeleton.presenter;


import android.content.Context;

import com.example.appskeleton.model.ModelBase;

/**
 * Created by yao on 2017/6/26.
 */

public class PresenterBase implements IPresenterBase {
    ModelBase mModel;
    public Context mContext;
    public String tag = "PresenterBase";
    public PresenterBase(Context context) {
        mContext = context;
        this.mModel = new ModelBase();
    }

    @Override
    public void release() {
        mModel.release();
        mContext = null;
    }
}
