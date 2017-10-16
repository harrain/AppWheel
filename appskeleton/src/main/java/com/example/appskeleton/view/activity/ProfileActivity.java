package com.example.appskeleton.view.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.appskeleton.R;
import com.example.appskeleton.R2;
import com.example.appskeleton.bean.listitem.ProfileItemBean;
import com.example.appskeleton.view.adapter.ProfileAdapter;
import com.example.appskeleton.view.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseTitleActivity {


    @BindView(R2.id.profile_rv)
    RecyclerView profileRv;
    private List<ProfileItemBean> list;
    private ProfileAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_profile);
        super.initView();
        ButterKnife.bind(this);
        setmTTitle("个人信息");

        initRecyclerView();
        initItemBean();
    }

    private void initItemBean() {
        list.add(new ProfileItemBean("头像",null,null,R.drawable.default_hd_avatar,0));
        list.add(new ProfileItemBean("昵称","晏城",null,0,1));
        list.add(new ProfileItemBean("性别",null,null,0,1));
        list.add(new ProfileItemBean("我的地址",null,null,0,1));
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new ProfileAdapter(mContext, list);
        profileRv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        profileRv.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
        profileRv.addItemDecoration(decoration);
    }


}
