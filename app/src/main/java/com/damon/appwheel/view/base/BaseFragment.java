package com.damon.appwheel.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damon.appwheel.view.widget.LoadingPage;
import com.damon.appwheel.view.widget.LoadingPage.LoadResult;
import com.damon.appwheel.view.util.ViewUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    private LoadingPage loadingPage;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        if (loadingPage == null) {  // 之前的frameLayout 已经记录了一个爹了  爹是之前的ViewPager
            loadingPage = new LoadingPage(getActivity()){

                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }


                public LoadResult load() {
                    return BaseFragment.this.load();
                }
            };
        }else{
            ViewUtil.removeParent(loadingPage);// 移除frameLayout之前的爹
        }

        return loadingPage;  //  拿到当前viewPager 添加这个framelayout
    }

    public void show(){
        if(loadingPage!=null){
            loadingPage.show();
        }
    }

    /***
     *  创建成功的界面
     * @return
     */
    public  abstract View createSuccessView();
    /**
     * 请求服务器
     * @return
     */
    public abstract LoadResult load();

    /**校验数据 */
    public LoadResult checkData(List datas) {
        if(datas==null){
            return LoadResult.error;//  请求服务器失败
        }else{
            if(datas.size()==0){
                return LoadResult.empty;
            }else{
                return LoadResult.success;
            }
        }

    }




}
