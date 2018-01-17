package com.damon.appwheel.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.PopupWindow;

import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.damon.appwheel.ui.fragment.FrontPageFragment;
import com.damon.appwheel.ui.fragment.MDShowFragment;
import com.damon.appwheel.R;
import com.damon.appwheel.constant.Url;
import com.damon.appwheel.presenter.InitConfigPresenter;
import com.damon.appwheel.util.LogUtils;
import com.damon.appwheel.ui.fragment.DisplayFragment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LaucherActivity extends MainActivity {

    FrontPageFragment mFrontPageFragment;
    DisplayFragment displayFragment;
    MDShowFragment mdShowFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    public static boolean onCreate = false;
    /**fragment里的数据源 ，如果点击某个item进入详情activity，详情activity又需要对fragment的数据源操作。这种情况就是跨域数据源
     需要跨域数据源，最好新建DataSources类，在里面创建静态变量或集合数组，供跨域操作*/
//    public static List<PacketInfo> infos = new ArrayList<>();

    private InitConfigPresenter initConfigPresenter;
    public static boolean firstLoadData = true;

    @Override
    public void initView() {
        super.initView();
        mFragmentManager = getSupportFragmentManager();

        mFrontPageFragment = new FrontPageFragment();
        displayFragment = new DisplayFragment();
        mdShowFragment = new MDShowFragment();
        setTitleMoreIcon(R.drawable.add_icon);


    }


    @Override
    public void onResume() {
        super.onResume();
        /** 检查网络*/
        checkNetStatus(radioGroup);

        if (firstEnter) {
            /** app第一次进入，检查配置，版本更新等*/
            initConfigPresenter = new InitConfigPresenter(mContext);
            initConfigPresenter.antoUpdateVersion(Url.appUpdateUrl, getResources().getString(R.string.app_name));
            firstEnter = false;

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(tag, "onStart");


    }


    @Override
    public void bottomTabSelected(int position) {
        super.bottomTabSelected(position);
        switch (position){
            case 0:
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fragment_container, mFrontPageFragment);
                mFragmentTransaction.commit();
                setmTTitle("hey首页");

                break;
            case 1:
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fragment_container,mdShowFragment);
                mFragmentTransaction.commit();
                setmTTitle("多栏");
                break;
            case 3:
                /** 切换fragment*/
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fragment_container, displayFragment);
                mFragmentTransaction.commit();
                /** 设置titlebar标题*/
                setmTTitle("我");

                break;
        }
    }

    /**
     *标题栏最右icon的点击操作
     */
    @Override
    public void moreAction() {
        super.moreAction();

    }

    /**
     *主activity销毁，App退出
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        /** 重置数据获取标志位，*/
        firstLoadData = true;
        /** 静态数据清空*/
//        infos.clear();
        /** 回收presenter*/
        if (initConfigPresenter != null) initConfigPresenter.release();
        LogUtils.i(tag, "onDestroy");
    }




}
