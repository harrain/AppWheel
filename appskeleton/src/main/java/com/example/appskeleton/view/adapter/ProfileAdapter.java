package com.example.appskeleton.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.appskeleton.R;
import com.example.appskeleton.bean.listitem.ProfileItemBean;
import com.example.appskeleton.view.itemDelegate.TextImageItemDelegate;
import com.example.appskeleton.view.itemDelegate.TextOnlyItemDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by data on 2017/10/16.
 */

public class ProfileAdapter extends MultiItemTypeAdapter<ProfileItemBean> {


    public ProfileAdapter(Context context, List<ProfileItemBean> datas) {
        super(context, datas);
        TextOnlyItemDelegate<ProfileItemBean> delegate = new TextOnlyItemDelegate<ProfileItemBean>() {

            @Override
            public void bindData(ViewHolder holder, ProfileItemBean o, int position) {
                if (!TextUtils.isEmpty(o.getText()))
                    holder.setText(R.id.item_tv_text,o.getText());
                if (!TextUtils.isEmpty(o.getDes()))
                    holder.setText(R.id.item_tv_des,o.getDes());
            }

            @Override
            public boolean viewTypeCondition(ProfileItemBean item, int position) {
                return item.itemType == 1;
            }
        };

        TextImageItemDelegate<ProfileItemBean> textImageItemDelegate = new TextImageItemDelegate<ProfileItemBean>() {
            @Override
            public void bindData(ViewHolder holder, ProfileItemBean o, int position) {
                if (!TextUtils.isEmpty(o.getText()))
                    holder.setText(R.id.item_tv_text,o.getText());
                if (!TextUtils.isEmpty(o.getImgPath()))
                    Glide.with(mContext).load(o.getImgPath()).into((ImageView) holder.getView(R.id.item_iv_des));

                ImageView imageView =  (ImageView) holder.getView(R.id.item_iv_des);

                if (o.getResId() != 0){}
                    Glide.with(mContext).load(o.getResId()).into(imageView);
            }

            @Override
            public boolean viewTypeCondition(ProfileItemBean item, int position) {
                return item.itemType == 0;
            }
        };

        addItemViewDelegate(textImageItemDelegate);//头像
        addItemViewDelegate(delegate);//昵称


    }



}
