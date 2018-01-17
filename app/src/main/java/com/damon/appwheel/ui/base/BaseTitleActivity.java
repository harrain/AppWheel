package com.damon.appwheel.ui.base;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.damon.appwheel.R;
import com.damon.appwheel.ui.util.UIConvertUtils;


/**
 * Created by stephen on 2017/7/25.
 */

public class BaseTitleActivity extends BaseActivity {


    public Toolbar mTitlebar;

    public TextView mTTitle;
    public ImageView mTBack;
    public ImageView mTMore;
    public TextView more_ib;

    @Override
    public void initView() {
        super.initView();
        initTitleBar();
    }

    public void initTitleBar() {
        mTitlebar = (Toolbar) findViewById(R.id.titlebar);
        setSupportActionBar(mTitlebar);
        mTTitle = (TextView) findViewById(R.id.toolbar_title);
        mTBack = (ImageView) findViewById(R.id.toolbar_back);
        mTMore = (ImageView) findViewById(R.id.toolbar_more);
        more_ib = (TextView) findViewById(R.id.toolbar_more_ib);

        mTBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        mTMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreAction();
            }
        });
        more_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreAction();
            }
        });
    }


    public void moreAction() {
    }

    public void setmTTitle(String text){
        mTTitle.setText(text);
    }

    public void setTitlebarBackgroundColor(int color){
//        mTitlebar.setBackgroundColor(color);
    }

    public void setTitleMoreIcon(int resId){
        mTMore.setImageResource(resId);
    }

    public void setmTBackHide(){
        mTBack.setVisibility(View.GONE);
    }

    public void setmTMoreHide(){
        mTMore.setVisibility(View.GONE);
    }

    public void setMoreTvVisible(){
        more_ib.setVisibility(View.VISIBLE);
        mTMore.setVisibility(View.GONE);
    }
    public void setTMoreVisible(){
        mTMore.setVisibility(View.VISIBLE);
        more_ib.setVisibility(View.GONE);
    }

    public void setMoreTvText(String text){
        more_ib.setText(text);
    }

    public void setMoreTvBackground(@DrawableRes int drawableId){
        more_ib.setPadding(UIConvertUtils.dp2px(5),UIConvertUtils.dp2px(5),UIConvertUtils.dp2px(5),UIConvertUtils.dp2px(5));
        more_ib.setBackgroundResource(drawableId);
    }
}
