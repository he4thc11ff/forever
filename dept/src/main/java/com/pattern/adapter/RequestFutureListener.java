package com.pattern.adapter;

public interface RequestFutureListener<T> {

    void onSuccess(T value);

}
