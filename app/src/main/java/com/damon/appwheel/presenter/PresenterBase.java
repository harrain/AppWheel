package com.damon.appwheel.presenter;


import com.damon.appwheel.model.ModelBase;

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
