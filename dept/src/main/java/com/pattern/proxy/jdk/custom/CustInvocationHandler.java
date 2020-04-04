package com.pattern.proxy.jdk.custom;

import java.lang.reflect.Method;

public interface CustInvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
