package com.damon.appwheel.ui.rlPart.holder;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.damon.appwheel.R;
import com.damon.appwheel.util.LogUtils;
import com.damon.appwheel.ui.rlPart.MeFragmentAdapter;
import com.damon.appwheel.ui.rlPart.base.AdapterDataOperation;
import com.damon.appwheel.ui.rlPart.base.BaseMyHolder;

import java.util.List;


/**
 * Created by data on 2017/8/23.
 */

public class TextOnOffHolder extends BaseMyHolder<List<MeFragmentAdapter.ItemModel>> {
    private final String tag = "TextOnOffHolder";
    TextView mContentTv;
    SwitchCompat mSc;


    public TextOnOffHolder(View itemView, Context context) {
        super(itemView, context);
        mContentTv = (TextView) itemView.findViewById(R.id.me_sc_tv_content);
        mSc = (SwitchCompat) itemView.findViewById(R.id.me_sc);

    }

    @Override
    public void bind(int position, AdapterDataOperation<List<MeFragmentAdapter.ItemModel>> ado) {
        try {
            mContentTv.setText(ado.getDatas().get(position).content[0]);
            mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
        }catch (Exception e){
            LogUtils.e(tag,e.getMessage());
        }
    }

}
