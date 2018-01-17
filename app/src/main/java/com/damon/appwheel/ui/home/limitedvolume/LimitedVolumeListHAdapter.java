package com.damon.appwheel.ui.home.limitedvolume;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.damon.appwheel.R;
import com.damon.appwheel.utils.Constant;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class LimitedVolumeListHAdapter extends CommonAdapter<LimitedVolume> {
  public LimitedVolumeListHAdapter(final Context context, List<LimitedVolume> data) {
    super(context,R.layout.item_hotlists_h_item, data);
  }



  @Override
  protected void convert(ViewHolder holder, LimitedVolume item, int position) {
    ImageView mImageView = holder.getView(R.id.item_img);
    Glide.with(mContext).load(Constant.IMAGE_HEAD_URL + item.Picture).into(mImageView);
    holder.setText(R.id.item_title,item.ProductName);
    holder.setText(R.id.item_type,item.PromotionInfoPrice);
  }
}
