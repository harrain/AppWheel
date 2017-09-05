package com.example.appskeleton.view.iview;

/**
 * Created by yao on 2017/6/26.
 */

public interface IViewBase<T> {
    void showResult(T t);

    void showError(String error);
}
