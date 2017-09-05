package com.example.appskeleton.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by stephen on 2016/11/5.
 */

public abstract class LoadingPage extends FrameLayout {

    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    public   int state = STATE_UNKNOWN;//状态state不能是static，
    // ，因为子类共用父类的静态变量，导致各子类state混淆
    private View loadingView;// 加载中的界面
    private View errorView;// 错误界面
    private View successView;// 加载成功的界面
    private View emptyView;// 空界面
    Context mContext;


    public LoadingPage(Context context) {
        super(context);
        init(context);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    // 在FrameLayout中 添加几种不同的界面
    private void init(Context context) {
        mContext = context;
        errorView = createErrorView(); // 加载错误界面
        if (errorView != null) {
            this.addView(errorView,
                    new LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
        }

        loadingView = createLoadingView(); // 创建了加载中的界面
        if (loadingView != null) {
            this.addView(loadingView,
                    new LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView(); // 加载空的界面
        if (emptyView != null) {
            this.addView(emptyView,
                    new LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
        }



    }

    // 根据不同的状态显示不同的界面
    private void showPage() {
        if (state == STATE_SUCCESS) {
            if (successView == null) {
                successView = createSuccessView();
                this.addView(successView, new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
            successView.setVisibility(View.VISIBLE);
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }

        if(errorView != null){
            errorView.setVisibility(state == STATE_ERROR ?
                    View.VISIBLE : View.INVISIBLE);
        }
        if(loadingView != null){
            loadingView.setVisibility(state == STATE_UNKNOWN || state == STATE_LOADING ?
                    View.VISIBLE : View.INVISIBLE);
        }
        if(emptyView != null){
            emptyView.setVisibility(state == STATE_EMPTY ?
                    View.VISIBLE : View.INVISIBLE);
        }



    }

    // 根据服务器的数据 切换状态
    public void show() {
        if (state == STATE_ERROR || state == STATE_EMPTY ) {
            state = STATE_LOADING;
        }
        // 请求服务器 获取服务器上数据 进行判断

        showPage();
    }

    public void show(int netState){
        state = netState;
        showPage();
    }


    public abstract View createSuccessView();

    private View createErrorView() {
//        View view = View.inflate(mContext, R.layout.loadpage_error,null);
//        Button page_bt = (Button) view.findViewById(R.id.page_bt);
//        page_bt.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show();
//            }
//        });
        return null;
    }

    private View createEmptyView() {
//        View view = View.inflate(mContext,R.layout.loading_empty,null);
        return null;
    }

    private View createLoadingView() {
//        View view = View.inflate(mContext,R.layout.loading_page,null);
        return null;
    }
}
