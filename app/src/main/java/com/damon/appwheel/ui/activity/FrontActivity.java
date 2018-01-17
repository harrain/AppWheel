package com.damon.appwheel.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.PopupWindow;

import com.damon.appwheel.R;
import com.damon.appwheel.constant.Url;
import com.damon.appwheel.model.util.SharedPrefrenceUtils;
import com.damon.appwheel.presenter.InitConfigPresenter;
import com.damon.appwheel.util.LogUtils;
import com.damon.appwheel.ui.fragment.DisplayFragment;

public class FrontActivity extends MainActivity {


    DisplayFragment displayFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private PopupWindow pw;
    private int cameraType = 0;

    public static boolean onCreate = false;
    /**声明数据库dao */
//    private PackageDao mDao;
//    private VegetableDao vdao;
    /**fragment里的数据源 ，如果点击某个item进入详情activity，详情activity又需要对fragment的数据源操作。这种情况就是跨域数据源
     需要跨域数据源，最好新建DataSources类，在里面创建静态变量或集合数组，供跨域操作*/
//    public static List<PacketInfo> infos = new ArrayList<>();

    private InitConfigPresenter initConfigPresenter;
    /**该presenter声明*/
    //    private FrontActivityPresenter frontActivityPresenter;
    public static boolean firstLoadData = true;

    @Override
    public void initView() {
        super.initView();
        mFragmentManager = getSupportFragmentManager();


        displayFragment = new DisplayFragment();
        LogUtils.i(tag, "initview");
        setTitleMoreIcon(R.drawable.add_icon);

        /**初始化数据库dao*/
//        mDao = new PackageDao();
//        vdao = new VegetableDao();

        /**显示默认首页fragment*/
//        mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.fragment_container,mDeliveryFragment);
//        mFragmentTransaction.commit();


    }

    /**
     *数据操纵实现iview接口，覆写一下方法
     */
//    @Override
//    public void showResult(Object o) {
    /** 数显*/
//        sortInfosAndShowFragment();
    /** 数据库写入*/
//        frontActivityPresenter.handleDBPackage();
    /** 停止刷新*/
//        if (mDeliveryFragment.getmSrl() != null) mDeliveryFragment.getmSrl().setRefreshing(false);
//    }
//
//    @Override
//    public void showError(String error) {
//        LogUtils.i(tag,"showError "+error);
    /** 断网从数据库读取数据*/
//        frontActivityPresenter.obtainPackageFromDB();
    /** 数显*/
//        sortInfosAndShowFragment();
//    }

    /**
     *数据显示
     */
//    private void sortInfosAndShowFragment() {
//        SortClass sort = new SortClass();
//        Collections.sort(infos,sort);
//        LogUtils.i(tag,"sortInfosAndShowFragment info size"+infos.size());
//        mDeliveryFragment.setStatusForLoadingPage(LoadingPage.STATE_SUCCESS);
//        mDeliveryFragment.showPage(LoadingPage.STATE_SUCCESS);
//        mDeliveryFragment.notifyDataSetChanged();
//    }

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

        if (SharedPrefrenceUtils.getInstance().isLogin()) {
            /** 登录情况下只获取一次数据*/
            if (firstLoadData) {
                LogUtils.i(tag, "firstLoadData ");
//                mDeliveryFragment.setStatusForLoadingPage(LoadingPage.STATE_SUCCESS);
//                mDeliveryFragment.showPage(LoadingPage.STATE_SUCCESS);
                loadInfoFromServer();
                firstLoadData = false;
            }
        } else {
            /** 未登录，则显示未登录页面*/
//            mDeliveryFragment.setStatusForLoadingPage(LoadingPage.STATE_UNLOGIN);
//            mDeliveryFragment.showPage(LoadingPage.STATE_UNLOGIN);
        }
    }

    public void loadInfoFromServer() {
//        frontActivityPresenter = new FrontActivityPresenter(mContext);
//        frontActivityPresenter.setIViewListener(this);
//        frontActivityPresenter.initData();
    }

    @Override
    public void onCheckedChange(View v) {
        super.onCheckedChange(v);
//        netTest();
        if (v.getId() == R.id.layout_personal_center) {

            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_container, displayFragment);
            mFragmentTransaction.commit();
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
//        if (frontActivityPresenter!=null)frontActivityPresenter.release();
//        mDao = null;
//        vdao = null;
        LogUtils.i(tag, "onDestroy");
    }


}
