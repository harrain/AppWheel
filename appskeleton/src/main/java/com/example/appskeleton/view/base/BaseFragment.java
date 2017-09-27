package com.example.appskeleton.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.view.widget.LoadingPage;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<E> extends Fragment {
    public String tag = "BaseFragment";
    public Context mContext;
    private LoadingPage loadingPage;
    public LayoutInflater inflater;
    public ArrayList<E> list;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContext = getActivity();
        this.inflater = inflater;
        list = new ArrayList<>();
        LogUtils.i(tag,"onCreateView");
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()){

                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView(BaseFragment.this.inflater,loadingPage);
                }

            };
        }

        return loadingPage;  //  拿到当前viewPager 添加这个framelayout
    }

    public void showPage(int state){

        if (loadingPage!=null) {
            loadingPage.show(state);
        }

    }

    /***
     *  创建成功的界面
     * @return
     * @param inflater
     * @param
     */
    public  abstract View createSuccessView(LayoutInflater inflater, ViewGroup container);

    public void setPageBackgroundColor(int color){loadingPage.setBackgroundColor(color);}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadingPage = null;
        inflater = null;
    }
}
