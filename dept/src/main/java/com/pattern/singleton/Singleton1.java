package com.pattern.singleton;

/**
 * 懒汉式单例类.在第一次调用的时候实例化自己
 * 线程不安全
 * @author Administrator
 *
 */
public class Singleton1{
	//1、第一步先将构造方法私有化（私有化的目的：不能让外部直接实例化）
	private Singleton1() {}
	//2、然后声明一个静态变量保存单例的引用
	private static Singleton1 single = null;
	//3、通过提供一个静态方法来获得单例的引用
	public static Singleton1 getInstance() {
		if (single == null) {
			single = new Singleton1();
		}
		return single;
	}
}
