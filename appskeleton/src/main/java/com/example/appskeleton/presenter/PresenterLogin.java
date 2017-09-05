package com.example.appskeleton.presenter;

import android.content.Context;

import com.example.appskeleton.bean.UserBean;
import com.example.appskeleton.model.OnCompleteListener;
import com.example.appskeleton.model.business.IModelLogin;
import com.example.appskeleton.model.business.ModelLogin;
import com.example.appskeleton.view.iview.IViewLogin;


/**
 * Created by yao on 2017/6/26.
 */

public class PresenterLogin extends PresenterBase implements IPresenterLogin {
    IModelLogin mModel;
    //应该还有一个View层暴露的接口
    IViewLogin mView;

    public PresenterLogin(IViewLogin view) {
        mModel=new ModelLogin();
        mView=view;
    }

    @Override
    public void login(Context context, String userName, String password) throws Exception{
        if (userName == null || userName.equals("")) {
            throw new Exception("请先注册");
        }
        if (password == null || password.equals("")) {
            throw new Exception("密码错误");
        }
        mModel.login(context, userName, password, new OnCompleteListener<UserBean>() {
            @Override
            public void onSuccess(UserBean user) {
                //编写登录成功的显示代码
                mView.showResult(user);
            }

            @Override
            public void onError(String error) {
                //编写登录失败的显示代码
                mView.showError(error);
            }
        });
    }

    @Override
    public void release() {
        super.release();
        mView=null;
    }
}
