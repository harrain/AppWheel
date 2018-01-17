package com.damon.appwheel.ui.rlPart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damon.appwheel.R;
import com.damon.appwheel.ui.rlPart.base.AdapterArrayListOperation;
import com.damon.appwheel.ui.rlPart.base.BaseAdapter;
import com.damon.appwheel.ui.rlPart.holder.LatlngActivityHolder;

import java.util.List;

/**
 * Created by stephen on 2017/8/14.
 */

public class ArrayListAdapter extends BaseAdapter<List<String>> {

    public ArrayListAdapter(Context context, List<String> data) {
        super(context);
        setAdapterDataOperation(new AdapterArrayListOperation<String>(data,this));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View swipeView = LayoutInflater.from(mContext).inflate(R.layout.simple_list_tv, parent, false);
        return new LatlngActivityHolder(swipeView,mContext);
    }
}
