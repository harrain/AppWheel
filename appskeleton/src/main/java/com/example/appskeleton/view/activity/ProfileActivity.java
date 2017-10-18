package com.example.appskeleton.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.appskeleton.R;
import com.example.appskeleton.R2;
import com.example.appskeleton.bean.listitem.ProfileItemBean;
import com.example.appskeleton.model.util.GalaryHelper;
import com.example.appskeleton.view.adapter.ProfileAdapter;
import com.example.appskeleton.view.base.BaseTitleActivity;
import com.lzy.imagepicker.bean.ImageItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseTitleActivity {


    @BindView(R2.id.profile_rv)
    RecyclerView profileRv;
    private List<ProfileItemBean> list;
    private ProfileAdapter adapter;
    private GalaryHelper galaryHelper;

    @Override
    public void initView() {
        setContentView(R.layout.activity_profile);
        super.initView();
        ButterKnife.bind(this);
        setmTTitle("个人信息");

        galaryHelper = new GalaryHelper(mContext);
        initRecyclerView();
        initItemBean();
    }

    private void initItemBean() {
        list.add(new ProfileItemBean("头像",null,null,R.drawable.default_hd_avatar,0));
//        list.add(new ProfileItemBean("头像",null,"/Android/data/com.damon.appwheel/cache/output_image_36777274.jpg",-1,0));
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

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (list.get(position).itemType == 0){
//                    galaryHelper.showChooseDialog();//系统原生方式，提示框，选择拍照还是相册，
                    galaryHelper.initCropLikeWXAvatar().pickPhoto();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        galaryHelper.handleResult(requestCode,resultCode, data, new GalaryHelper.ShowImageListener() {
            @Override
            public void showImage(String imagePath, Uri uri) {
                if (uri!=null){
                    list.get(0).setUri(uri);
                }else {
                    list.get(0).setImgPath(imagePath);
                }
                list.get(0).setResId(0);
                adapter.notifyItemChanged(0);
            }
        });
        galaryHelper.handleWXPickerData(requestCode,resultCode, data, new GalaryHelper.ImagePickerResultListener() {
            @Override
            public void showImage(List<ImageItem> imageItemList) {
                ImageItem item = imageItemList.get(0);
                list.get(0).setImg(new File(item.path));
                adapter.notifyItemChanged(0);


            }
        });
    }
}
