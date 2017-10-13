package com.damon.appwheel.utils.http;

import com.damon.appwheel.utils.HttpsResult;

import io.reactivex.functions.Function;

public class HttpResultFunc<T> implements Function<HttpsResult<T>, T> {

  @Override public T apply(HttpsResult<T> httpResult) throws Exception {
    if (!httpResult.success) {
      throw new ApiException(100);
    }
    return httpResult.content;
  }
}