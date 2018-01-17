package com.damon.appwheel.model;


import com.damon.appwheel.model.net.OkUtils;

/**
 * Created by yao on 2017/6/26.
 */

public class ModelBase implements IModelBase {
    @Override
    public void release() {
        OkUtils.release();
    }
}
