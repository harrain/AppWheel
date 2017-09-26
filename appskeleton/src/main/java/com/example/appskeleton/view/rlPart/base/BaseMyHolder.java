package com.example.appskeleton.view.rlPart.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

    /**
     * Created by stephen on 2017/8/13.
     */

public  class BaseMyHolder<DT> extends RecyclerView.ViewHolder {
    public Context mContext;
    public BaseAdapter.OnClickListener mOnClickListener;
    public String tag;

        public BaseMyHolder(View itemView) {
            super(itemView);
        }

        public BaseMyHolder(View itemView,Context context){
            this(itemView);
            mContext = context;
        }



    public void bind(int position, AdapterDataOperation<DT> ado){}


    public void setOnClickListener(BaseAdapter.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public BaseAdapter.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }
}
