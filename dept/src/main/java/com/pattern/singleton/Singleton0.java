package com.pattern.singleton;

/**
 * 饿汉
 * 
 * @author Administrator
 *
 */
public class Singleton0 implements java.io.Serializable {   
	public static Singleton0 INSTANCE = new Singleton0();   
	protected Singleton0() {  }   
	
	//readResolve 解决问题：如果单例实现了Serializable接口之后，单例会被反序列化破坏的问题
	private Object readResolve() {
		return INSTANCE;
	}
}
