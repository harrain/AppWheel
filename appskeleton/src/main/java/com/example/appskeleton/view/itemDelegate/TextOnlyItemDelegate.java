package com.example.appskeleton.view.itemDelegate;

import com.example.appskeleton.R;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by data on 2017/10/16.
 */

public abstract class TextOnlyItemDelegate<T> implements ItemViewDelegate<T> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.textonly_itemdelegate;
    }

    @Override
    public boolean isForViewType(T item, int position) {
        return viewTypeCondition(item, position);
    }

    @Override
    public void convert(ViewHolder holder, T o, int position) {
        bindData(holder, o, position);
    }

    public abstract void bindData(ViewHolder holder, T o, int position);
    public abstract boolean viewTypeCondition(T item, int position);
}
