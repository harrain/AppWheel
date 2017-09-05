package com.example.appskeleton.view.base;

import android.view.View;

import com.example.appskeleton.view.util.ViewParentUtils;
import com.example.appskeleton.view.widget.LoadingPage;

/**
 * Created by stephen on 2017/7/25.
 */

public abstract class BaseNetStatusShowActivity extends BaseRefreshActivity {

    private LoadingPage loadingPage;

    @Override
    public void initView() {
        super.initView();
        initLoadingPage();
    }

    private void initLoadingPage() {
        if (loadingPage == null) {  // 之前的frameLayout 已经记录了一个爹了  爹是之前的ViewPager
            loadingPage = new LoadingPage(this){

                @Override
                public View createSuccessView() {
                    return BaseNetStatusShowActivity.this.createSuccessView();
                }

            };
        }else{
            ViewParentUtils.removeParent(loadingPage);// 移除frameLayout之前的爹
        }
    }

    public abstract View createSuccessView();
}
