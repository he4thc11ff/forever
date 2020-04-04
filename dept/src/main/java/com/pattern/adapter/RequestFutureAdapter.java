package com.pattern.adapter;

/**
 * Adapt from a request future of one type to another.
 *
 * @param <F> Type to adapt from
 * @param <T> Type to adapt to
 */
public abstract class RequestFutureAdapter<F, T> {
    public abstract void onSuccess(F value, RequestFuture<T> requestFuture);
}
