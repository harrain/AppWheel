package com.damon.appwheel.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.damon.appwheel.R;
import com.damon.appwheel.bean.listitem.ProfileItemBean;
import com.damon.appwheel.ui.itemDelegate.TextImageItemDelegate;
import com.damon.appwheel.ui.itemDelegate.TextOnlyItemDelegate;
import com.damon.appwheel.ui.util.GlideUtil;
import com.damon.appwheel.ui.util.UIConvertUtils;
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
                    GlideUtil.showImage(mContext,o.getImgPath(),(ImageView) holder.getView(R.id.item_iv_des)
                    , UIConvertUtils.dp2px(40),UIConvertUtils.dp2px(40));


                ImageView imageView =  (ImageView) holder.getView(R.id.item_iv_des);

                if (o.getResId() > 0){

                    GlideUtil.showImage(mContext,o.getResId(),(ImageView) holder.getView(R.id.item_iv_des)
                            , UIConvertUtils.dp2px(40),UIConvertUtils.dp2px(40));
                }

                if (o.getUri()!=null){

                    GlideUtil.showImage(mContext,o.getUri(),(ImageView) holder.getView(R.id.item_iv_des)
                            , UIConvertUtils.dp2px(40),UIConvertUtils.dp2px(40));
                }
                if (o.getImg() != null){
                    GlideUtil.showImage(mContext,o.getImg(),(ImageView) holder.getView(R.id.item_iv_des)
                            , UIConvertUtils.dp2px(40),UIConvertUtils.dp2px(40));
                }
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
