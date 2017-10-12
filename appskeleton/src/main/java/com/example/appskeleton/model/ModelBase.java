package com.example.appskeleton.model;


import com.example.appskeleton.model.net.OkUtils;

/**
 * Created by yao on 2017/6/26.
 */

public class ModelBase implements IModelBase {
    @Override
    public void release() {
        OkUtils.release();
    }
}
