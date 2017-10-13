package com.example.appskeleton.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.PopupWindow;

import com.example.appskeleton.R;
import com.example.appskeleton.constant.Url;
import com.example.appskeleton.model.util.SharedPrefrenceUtils;
import com.example.appskeleton.presenter.InitConfigPresenter;
import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.view.fragment.DisplayFragment;

public class FrontActivity extends MainActivity {


    DisplayFragment displayFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private PopupWindow pw;
    private int cameraType = 0;

    public static boolean onCreate = false;
//    private PackageDao mDao;
//    private VegetableDao vdao;
//    public static List<PacketInfo> infos = new ArrayList<>();

    private InitConfigPresenter initConfigPresenter;
    //    private FrontActivityPresenter frontActivityPresenter;
    public static boolean firstLoadData = true;

    @Override
    public void initView() {
        super.initView();
        mFragmentManager = getSupportFragmentManager();


        displayFragment = new DisplayFragment();
        LogUtils.i(tag, "initview");
        setTitleMoreIcon(R.drawable.add_icon);

//        mDao = new PackageDao();
//        vdao = new VegetableDao();

//        mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.fragment_container,mDeliveryFragment);
//        mFragmentTransaction.commit();


    }


//    @Override
//    public void showResult(Object o) {
//        sortInfosAndShowFragment();
//        frontActivityPresenter.handleDBPackage();
//        if (mDeliveryFragment.getmSrl() != null) mDeliveryFragment.getmSrl().setRefreshing(false);
//    }
//
//    @Override
//    public void showError(String error) {
//        LogUtils.i(tag,"showError "+error);
//        frontActivityPresenter.obtainPackageFromDB();
//        sortInfosAndShowFragment();
//    }
//
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
        checkNetStatus(radioGroup);

        if (firstEnter) {

            initConfigPresenter = new InitConfigPresenter(mContext);
            initConfigPresenter.antoUpdateVersion(Url.appUpdateUrl, getResources().getString(R.string.app_name));
            firstEnter = false;

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(tag, "onStart");
        checkNetStatus(mTitlebar);
        if (SharedPrefrenceUtils.getInstance().isLogin()) {

            if (firstLoadData) {
                LogUtils.i(tag, "firstLoadData ");
//                mDeliveryFragment.setStatusForLoadingPage(LoadingPage.STATE_SUCCESS);
//                mDeliveryFragment.showPage(LoadingPage.STATE_SUCCESS);
                loadInfoFromServer();
                firstLoadData = false;
            }
        } else {
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


    @Override
    public void moreAction() {
        super.moreAction();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        firstLoadData = true;
//        infos.clear();
        if (initConfigPresenter != null) initConfigPresenter.release();
//        if (frontActivityPresenter!=null)frontActivityPresenter.release();
//        mDao = null;
//        vdao = null;
        LogUtils.i(tag, "onDestroy");
    }


}
