package com.example.appskeleton.view.fragment;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appskeleton.R;
import com.example.appskeleton.R2;
import com.example.appskeleton.model.util.SharedPrefrenceUtils;
import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.util.ToastUtil;
import com.example.appskeleton.view.activity.DeviceActivity;
import com.example.appskeleton.view.activity.FrontActivity;
import com.example.appskeleton.view.activity.LoginActivity;
import com.example.appskeleton.view.activity.ProfileActivity;
import com.example.appskeleton.view.rlPart.MeFragmentAdapter;
import com.example.appskeleton.view.rlPart.base.BaseAdapter;
import com.example.appskeleton.view.util.AlertDialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R2.id.me_rv)
    RecyclerView meRv;
    private Context mContext;
    private List<MeFragmentAdapter.ItemModel> mItemList;
    private final String tag = "DisplayFragment";
    private MeFragmentAdapter adapter;
//    private PackageDao packageDao;
//    private VegetableDao vegetableDao;

    public DisplayFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();

        initItemModel();
        initRecyclerView();
//        packageDao = new PackageDao();
//        vegetableDao = new VegetableDao();

        return view;
    }
    private void initItemModel() {
        mItemList.add(new MeFragmentAdapter.ItemModel(MeFragmentAdapter.HEAD_LAYOUT
                ,new String[]{"","账号 未登录"},new Intent(mContext, ProfileActivity.class)));
        mItemList.add(new MeFragmentAdapter.ItemModel(MeFragmentAdapter.EMPTY_LAYOUT,null));
//        mItemList.add(new MeFragmentAdapter.ItemModel(MeFragmentAdapter.TEXT_ONOFF_LAYOUT
//                ,new String[]{"自动定位"}));
        mItemList.add(new MeFragmentAdapter.ItemModel(MeFragmentAdapter.TEXTONLY_LAYOUT
                ,new String[]{"设备信息"},new Intent(mContext,DeviceActivity.class)));
//        mItemList.add(new MeFragmentAdapter.ItemModel(MeFragmentAdapter.TEXTONLY_LAYOUT,new String[]{"关于应用"}));

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initRecyclerView() {
        LogUtils.i(tag,"shuju "+mItemList.size());
        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        meRv.setLayoutManager(lm);
        DividerItemDecoration decor = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
//        decor.setDrawable(getResources().getDrawable(R.drawable.itemdecoration_rv,null));//分割条Drawable
        meRv.addItemDecoration(decor);
        adapter = new MeFragmentAdapter(mContext,mItemList);
        adapter.setOnClickListener(new BaseAdapter.OnClickListener() {
            @Override
            public void onShortClick(View v, int position) {
                LogUtils.i(tag,"onShortClick");
                Intent i = mItemList.get(position).getIntent();
                if (i!=null){
                    startActivity(i);
                }
                if (mItemList.get(position).content[0].equals("退出账号")){
                    exitForLogon();
                }
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        });
        meRv.setAdapter(adapter);

    }



    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(tag,"onStart");
        if (SharedPrefrenceUtils.getInstance().isLogin()){
            String username = SharedPrefrenceUtils.getInstance().getUsername();
            LogUtils.i(tag,"onStart","username "+username);
            mItemList.get(0).content = new String[]{"","账号 "+username};
            mItemList.get(0).intent = null;
            mItemList.add(new MeFragmentAdapter.ItemModel(MeFragmentAdapter.TEXTONLY_LAYOUT,new String[]{"退出账号"}));
            adapter.notifyDataSetChanged();
        }
//        else {
//            mItemList.get(0).intent = new Intent(mContext, LoginActivity.class);
//            mItemList.remove(3);
//            adapter.notifyDataSetChanged();
//        }
    }

    private void exitForLogon() {
        AlertDialogUtil.showAlertDialogTwo(mContext, "提示", "即将退出当前账号", new AlertDialogUtil.AlertListener() {
            @Override
            public void positiveResult(DialogInterface dialog, int which) {
                dialog.dismiss();

                //用户资料卡界面重置
                mItemList.get(0).content = new String[]{"","账号 未登录"};
                mItemList.get(0).intent = new Intent(mContext, LoginActivity.class);
                mItemList.remove(3);
                adapter.notifyDataSetChanged();

                //登陆状态记录重置
                SharedPrefrenceUtils.getInstance().setLoginState(false);
                //司机id 由用户登陆获取的数据重置
                SharedPrefrenceUtils.getInstance().removeDriverId();
                ToastUtil.showShortToast("您已经退出账号,请重新登陆");
                startActivity(new Intent(mContext,LoginActivity.class));
                //清除司机的配送列表数据库表
//                packageDao.deleteAll();
//                vegetableDao.deleteAll();
                //frontactivity重新获取数据源
                FrontActivity.firstLoadData = true;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
