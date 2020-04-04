package com.effective.section10.demo67;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class ObservableSet<E> extends ForwardingSet<E> {

    public ObservableSet(Set<E> s) {
        super(s);
    }

    private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    // This method is the culprit
    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers)
                observer.added(this, element);
        }
    }

    private void notifyElementAdded2(E element) {
        List<SetObserver<E>> snapshot = null;
        synchronized (observers) {
            //这里拍一个快照,这样我们遍历的时候就不用对原来的集合进行上锁了
            snapshot = new ArrayList<SetObserver<E>>(observers);
        }
        for (SetObserver<E> observer : snapshot)
            observer.added(this, element);
    }

    @Override
    public boolean add(E e) {
        //调用父类函数添加到集合中
        boolean added = super.add(e);
        if (added) {
            //添加成功,观察者保存注册对象
            notifyElementAdded(e);
        }

        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c) {
            //做或运算，只要有一个add添加成功，那么result就是true
            result |= add(element);
        }
        return result;
    }
}