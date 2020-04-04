package com.effective.section5.demo28;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

public class Stack<E> {

	
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITAL_CAPACITY = 16;
    
    public Stack() {
        elements = new Object[DEFAULT_INITAL_CAPACITY];
    }
    
    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }
    public int size() {
    	return size;
    }
    
    public Object popBad() {
        if(size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }
    public E pop() {
    	Object result = elements[--size];
        elements[size] = null;//显式地清空引用
        return (E) result;
    }
    
    private void ensureCapacity() {
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
	public boolean isEmpty(){
		return size>0?false:true;
	}
	
	
	//如果参数化类型表示一个T生产者，就使用<? extends T>；如果它表示一个T消费者，就使用<? super T>
//	public void pushAll(Iterable<E> src) {
	public void pushAll(Iterable<? extends E> src) {
		for (E e: src)
			push(e);
	}
	
	public void popAll(Collection<? super E> dst) {
	    while(!isEmpty()) {
	        dst.add(pop());
	    }
	}

} 
