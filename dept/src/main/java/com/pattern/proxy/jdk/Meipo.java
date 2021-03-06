package com.pattern.proxy.jdk;

import com.pattern.proxy.bean.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//媒婆
public class Meipo implements InvocationHandler {
	
	private Person target; //被代理对象的引用作为一个成员变量保存下来了
	
	//获取被代理人的个人资料
	public Object getInstance(Person target) throws Exception{
		this.target = target;
		Class clazz = target.getClass();
		System.out.println("被代理对象的class是:"+clazz);
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
	}
	

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("我是jdk媒婆：得给你找个异性才行");
		System.out.println("------begin------");
		//调用的时候
		method.invoke(this.target, args);
		System.out.println("------end------");
		System.out.println("如果合适的话，就准备办事");
		
		return null;
	}

}
