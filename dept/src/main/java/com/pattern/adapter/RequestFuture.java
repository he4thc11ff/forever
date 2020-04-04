package com.pattern.adapter;

import java.util.ArrayList;
import java.util.List;

public class RequestFuture<T> {

    private boolean isDone = false;
    private T value;
    private List<RequestFutureListener<T>> listeners = new ArrayList<>();

    public void complete(T value) {
        if (isDone)
            throw new IllegalStateException("Invalid attempt to complete a request future which is already complete");
        this.isDone = true;
        this.value = value;

        fireSuccess();
    }

    private void fireSuccess() {
        for (RequestFutureListener<T> listener: listeners) {
            listener.onSuccess(value);
        }
    }

    public <S> RequestFuture<S> compose(final RequestFutureAdapter<T, S> adapter) {
        RequestFuture<S> adapted = new RequestFuture<>();
        addListener(new RequestFutureListener<T>() {
            @Override
            public void onSuccess(T value) {
                adapter.onSuccess(value, adapted);
            }
        });

        return adapted;
    }

    public void addListener(RequestFutureListener<T> listener) {
        if (isDone) { // 如果done完之后才添加listener(先send再compose)，则直接执行
            listener.onSuccess(value);
        } else {
            this.listeners.add(listener);
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
