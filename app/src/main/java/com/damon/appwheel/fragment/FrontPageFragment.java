package com.damon.appwheel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.damon.appwheel.R;
import com.damon.appwheel.home.adapter.EditBetterHeaderAdapter;
import com.damon.appwheel.home.adapter.HeaderAdapter;
import com.damon.appwheel.home.adapter.HotListAdapter;
import com.damon.appwheel.home.adapter.LimitedVolumeAdapter;
import com.damon.appwheel.home.adapter.TypeAdapter;
import com.damon.appwheel.home.editbetter.EditBetter;
import com.damon.appwheel.home.editbetter.EditBetterAdapter;
import com.damon.appwheel.home.editbetter.EditBetterListModel;
import com.damon.appwheel.home.hotlist.HotListModel;
import com.damon.appwheel.home.hotlist.HotThing;
import com.damon.appwheel.home.limitedvolume.LimitedVolume;
import com.damon.appwheel.home.limitedvolume.LimitedVolumeModel;
import com.damon.appwheel.utils.http.ProgressSubscriber;
import com.damon.appwheel.utils.http.SubscriberOnNextListener;
import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.view.base.BaseFragment;
import com.example.appskeleton.view.widget.LoadingPage;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by data on 2017/10/13.
 */

public class FrontPageFragment extends BaseFragment {


    RecyclerView mList;
    private SubscriberOnNextListener<List<HotThing>> getTopMovieOnNext;
    private HotListAdapter mHotListAdapter;
    private LimitedVolumeAdapter mLimitedVolumeAdapter;
    private EditBetterHeaderAdapter mEditBetterHeaderAdapter;
    private EditBetterAdapter mEditBetterAdapter;

    private Unbinder unbinder;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showPage(LoadingPage.STATE_SUCCESS);
    }

    @Override
    public View createSuccessView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_frontpage,container,false);
        unbinder = ButterKnife.bind(this,view);
        mList = (RecyclerView) view.findViewById(R.id.list);
        initView();
        initData();
        return view;
    }

    private void initData() {
        getTopMovieOnNext = new SubscriberOnNextListener<List<HotThing>>() {
            @Override public void onNext(List<HotThing> mHotThings) {
                mHotListAdapter.setHotListData(mHotThings);
            }
        };
        SubscriberOnNextListener<List<LimitedVolume>> mSubscriberOnNextListener =
                new SubscriberOnNextListener<List<LimitedVolume>>() {
                    @Override public void onNext(List<LimitedVolume> mHotThings) {
                        mLimitedVolumeAdapter.setLimitedVolumeListData(mHotThings);
                    }
                };
        SubscriberOnNextListener<List<EditBetter>> mListener =
                new SubscriberOnNextListener<List<EditBetter>>() {
                    @Override public void onNext(List<EditBetter> mEditBetters) {
                        mEditBetterAdapter.setList(mEditBetters);
                    }
                };
        LimitedVolumeModel.getLimitedVolumeList(
                new ProgressSubscriber<List<LimitedVolume>>(mSubscriberOnNextListener, mContext));
        HotListModel.getHotList(
                new ProgressSubscriber<List<HotThing>>(getTopMovieOnNext, mContext));//精选
        EditBetterListModel.getEditBetterList(
                new ProgressSubscriber<List<EditBetter>>(mListener, mContext));
        
    }

    private void initView() {
        LogUtils.i(tag,"initview","recyclerview "+mList.toString());
        //绑定VirtualLayoutManager
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);
        mList.setLayoutManager(layoutManager);
        //设置Adapter列表
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        //初始化各个类型的adapter，并将适合的layoutHelper注入
        setHeader(adapters);
        setTypes(adapters);
        setHotLists(adapters);//辣榜
        setLimitedVolume(adapters);//限时优惠
        setEditBetterHeader(adapters);//精选的头部
        setEditBetterList(adapters);//精选的线性列表
        bind(layoutManager, adapters);//将Adapter集合绑定到DelegateAdapter,再将DelegateAdapter绑定到RecyclerView
    }

    private void setEditBetterList(List<DelegateAdapter.Adapter> mAdapters) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(10);//item之间的间隙，好用。不用addItemDecoration
        mEditBetterAdapter = new EditBetterAdapter(mContext, linearLayoutHelper);
        mAdapters.add(mEditBetterAdapter);
    }

    private void setEditBetterHeader(List<DelegateAdapter.Adapter> mAdapters) {
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setMarginBottom(10);
        mEditBetterHeaderAdapter = new EditBetterHeaderAdapter(mContext, singleLayoutHelper, 1);
        mAdapters.add(mEditBetterHeaderAdapter);
    }

    private void setLimitedVolume(List<DelegateAdapter.Adapter> mAdapters) {
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setMarginBottom(10);
        mLimitedVolumeAdapter = new LimitedVolumeAdapter(mContext, singleLayoutHelper, 1);
        mAdapters.add(mLimitedVolumeAdapter);
    }

    private void bind(VirtualLayoutManager mLayoutManager, List<DelegateAdapter.Adapter> mAdapters) {
        //绑定delegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(mLayoutManager);
        delegateAdapter.setAdapters(mAdapters);
        mList.setAdapter(delegateAdapter);
    }

    private void setHotLists(List<DelegateAdapter.Adapter> mAdapters) {
        //设置通栏布局
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setMarginBottom(10);
        mHotListAdapter = new HotListAdapter(mContext, singleLayoutHelper, 1);
        mAdapters.add(mHotListAdapter);
    }

    private void setTypes(List<DelegateAdapter.Adapter> mAdapters) {
        //设置Grid布局
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        //是否自动扩展
        gridLayoutHelper.setAutoExpand(false);
        gridLayoutHelper.setMarginBottom(10);
        //自定义设置某些位置的Item的占格数
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return 1;
            }
        });
        mAdapters.add(new TypeAdapter(mContext, gridLayoutHelper));
    }

    private void setHeader(List<DelegateAdapter.Adapter> mAdapters) {
        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(10);
        mAdapters.add(new HeaderAdapter(mContext, linearLayoutHelper, 1));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{

            if (unbinder!=null)
                unbinder.unbind();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
