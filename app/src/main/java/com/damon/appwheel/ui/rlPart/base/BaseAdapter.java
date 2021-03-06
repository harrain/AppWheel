package com.damon.appwheel.ui.rlPart.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


/**
 * 列表内容适配器，维系数据源和布局的显示
 */

public class BaseAdapter<DT> extends RecyclerView.Adapter <RecyclerView.ViewHolder>{


    public Context mContext;

//    private AdapterLinkOperation<DT> mADO;
    public AdapterDataOperation mADO;


    public OnClickListener listener;
    public    String tag = "BaseAdapter";

    public BaseAdapter(Context context) {
        mContext = context;
//        mADO = new AdapterLinkOperation<DT>(data,this);
    }

    public void setAdapterDataOperation(AdapterDataOperation<DT> dataOperation){
        mADO = dataOperation;
    }

    public interface OnClickListener{
        void onShortClick(View v, int position);
        void onLongClick(View v, int position);
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((BaseMyHolder)holder).setOnClickListener(listener);
        ((BaseMyHolder)holder).bind(position,mADO);
//        LogUtils.i("baseUDAdapter","mADO.getDatas().size:  "+mADO.getDatas().size());
    }


    @Override
    public int getItemCount() {

        return mADO.getSafeCount();
    }


}
