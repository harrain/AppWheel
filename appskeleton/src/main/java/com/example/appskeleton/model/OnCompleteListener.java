package com.example.appskeleton.model;

/**
 * Created by stephen on 2017/7/25.
 */

public interface OnCompleteListener<T> {
    void onSuccess(T result);

    void onError(String error);
}
