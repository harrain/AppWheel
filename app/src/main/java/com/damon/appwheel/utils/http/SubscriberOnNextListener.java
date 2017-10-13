package com.damon.appwheel.utils.http;

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}