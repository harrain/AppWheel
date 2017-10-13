package com.example.appskeleton.model.business;

import android.content.Context;

import com.example.appskeleton.bean.json.LoginResponse;
import com.example.appskeleton.model.ModelBase;
import com.example.appskeleton.model.OnCompleteListener;
import com.example.appskeleton.model.net.OkUtils;

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
