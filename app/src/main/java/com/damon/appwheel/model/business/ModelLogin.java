package com.damon.appwheel.model.business;

import android.content.Context;

import com.damon.appwheel.bean.json.LoginResponse;
import com.damon.appwheel.model.ModelBase;
import com.damon.appwheel.model.OnCompleteListener;
import com.damon.appwheel.model.net.OkUtils;

/**
 * Created by yao on 2017/6/26.
 */

public class ModelLogin extends ModelBase implements IModelLogin {
    @Override
    public void login(Context context, String userName, String password,OnCompleteListener<LoginResponse> listener) {
        OkUtils<LoginResponse> okUtils = new OkUtils<>(context);
        okUtils.url(null).addParam(null,null).addParam(null,null).targetClass(LoginResponse.class).execute(listener);
    }
}
