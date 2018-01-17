package com.damon.appwheel.ui.base;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.damon.appwheel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stephen on 2017/7/25.
 */

public class BaseRefreshActivity extends BaseTitleActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_fl)
    FrameLayout refreshFl;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    public void initView() {
        setContentView(R.layout.activity_freshlayout);
        super.initView();
        ButterKnife.bind(this);
        //设置进度条的颜色，不定长参数可以设置多种颜色
//对于RefreshLayout，网上有人说最多4种颜色，不要使用android.R.color.，否则会卡死
        srl.setColorSchemeColors(Color.BLACK);
//设置进度条的背景颜色
        srl.setProgressBackgroundColorSchemeColor(Color.WHITE);
//设置大小
        srl.setSize(SwipeRefreshLayout.LARGE);

        srl.setOnRefreshListener(this);

    }


    @Override
    public void onRefresh() {
        srl.setRefreshing(false);//停止刷新
    }
}
