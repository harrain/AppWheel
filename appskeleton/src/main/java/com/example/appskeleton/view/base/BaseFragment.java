package com.example.appskeleton.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appskeleton.view.widget.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    public String tag = "BaseFragment";
    public Context mContext;
    public LayoutInflater inflater;
    private LoadingPage loadingPage;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContext = getActivity();
        this.inflater = inflater;
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







}
