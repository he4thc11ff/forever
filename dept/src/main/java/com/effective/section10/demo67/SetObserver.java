package com.effective.section10.demo67;


public interface SetObserver<E>
{
    /**
     * 当一个元素添加到ObservableSet对象中的时候，调用
     * @param set
     * @param element
     */
    void added(ObservableSet<E> set, E element);
}