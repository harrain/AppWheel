package com.example.appskeleton.presenter;

import android.content.Context;

/**
 * Created by yao on 2017/6/26.
 */

public interface IPresenterLogin extends IPresenterBase {
    void login(Context context, String userName, String password) throws Exception;
}
