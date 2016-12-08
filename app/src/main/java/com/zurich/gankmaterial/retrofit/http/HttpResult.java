package com.zurich.gankmaterial.retrofit.http;

/**
 * 统一处理请求异常
 * Created by weixinfei on 16/7/5.
 */
public class HttpResult<T> {
    private boolean error;

    private T results;

    public boolean isResultCode() {
        return !error;
    }

    public T getResults() {
        return results;
    }

}
