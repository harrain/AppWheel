package com.damon.appwheel.model.operation;

import android.content.Context;

import com.damon.appwheel.bean.UserBean;
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
    void login(Context context, String userName, String password, OnCompleteListener<UserBean> listener);
}
