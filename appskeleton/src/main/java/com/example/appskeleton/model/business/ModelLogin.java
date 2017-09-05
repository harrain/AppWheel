package com.example.appskeleton.model.business;

import android.content.Context;

import com.example.appskeleton.bean.UserBean;
import com.example.appskeleton.model.ModelBase;
import com.example.appskeleton.model.OnCompleteListener;

/**
 * Created by yao on 2017/6/26.
 */

public class ModelLogin extends ModelBase implements IModelLogin {
    @Override
    public void login(Context context, String userName, String password,OnCompleteListener<UserBean> listener) {

    }
}
