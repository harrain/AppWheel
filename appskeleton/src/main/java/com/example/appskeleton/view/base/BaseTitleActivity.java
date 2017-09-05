package com.example.appskeleton.view.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appskeleton.R;


/**
 * Created by stephen on 2017/7/25.
 */

public class BaseTitleActivity extends BaseActivity {


    public Toolbar mTitlebar;

    public TextView mTTitle;
    public ImageView mTBack;
    public ImageView mTMore;

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

        mTBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTMore.setOnClickListener(new View.OnClickListener() {
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

    public void setTitleMore(int resId){
        mTMore.setImageResource(resId);
    }

    public void setmTBackHide(){
        mTBack.setVisibility(View.GONE);
    }

    public void setmTMoreHide(){
        mTMore.setVisibility(View.GONE);
    }
}