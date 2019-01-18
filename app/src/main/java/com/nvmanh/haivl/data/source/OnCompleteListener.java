package com.nvmanh.haivl.data.source;

public interface OnCompleteListener<T> {
    void onSuccess(T t);
    void onFail(Throwable throwable);
}
