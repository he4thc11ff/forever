package com.effective.section2.demo6;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITAL_CAPACITY = 16;
    
    public Stack() {
        elements = new Object[DEFAULT_INITAL_CAPACITY];
    }
    
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    
    public Object popBad() {
        if(size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }
    public Object pop() {
    	Object result = elements[--size];
        elements[size] = null;//显式地清空引用
        return result;
    }
    
    private void ensureCapacity() {
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
