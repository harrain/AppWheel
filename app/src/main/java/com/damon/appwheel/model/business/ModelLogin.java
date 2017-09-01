package com.damon.appwheel.model.business;

import android.content.Context;

import com.damon.appwheel.bean.UserBean;
import com.damon.appwheel.model.ModelBase;
import com.damon.appwheel.model.OnCompleteListener;

/**
 * Created by yao on 2017/6/26.
 */

public class ModelLogin extends ModelBase implements IModelLogin {
    @Override
    public void login(Context context, String userName, String password,OnCompleteListener<UserBean> listener) {

    }
}
