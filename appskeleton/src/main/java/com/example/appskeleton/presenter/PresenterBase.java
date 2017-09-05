package com.example.appskeleton.presenter;


import com.example.appskeleton.model.ModelBase;

/**
 * Created by yao on 2017/6/26.
 */

public class PresenterBase implements IPresenterBase {
    ModelBase mModel;

    public PresenterBase() {
        this.mModel = new ModelBase();
    }

    @Override
    public void release() {
        mModel.release();
    }
}
