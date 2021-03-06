package com.damon.appwheel.model.business;

import android.content.Context;

import com.damon.appwheel.bean.json.LoginResponse;
import com.damon.appwheel.model.IModelBase;
import com.damon.appwheel.model.OnCompleteListener;


/**
 * Created by yao on 2017/6/26.
 */

public interface IModelLogin extends IModelBase {
    /**
     * 登录请求
     * @param context
     * @param userName
     * @param password
     * @param listener
     */
    void login(Context context, String userName, String password, OnCompleteListener<LoginResponse> listener);
}
