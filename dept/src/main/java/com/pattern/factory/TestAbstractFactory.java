package com.pattern.factory;

import com.pattern.factory.abstr.DefaultFactory;

public class TestAbstractFactory {

	public static void main(String[] args) {
		//抽象工厂:解决了方法工厂的问题（把工厂抽象）
		DefaultFactory factory = new DefaultFactory();
		
		System.out.println(factory.getCar("Benz"));
		
		//设计模式的经典之处，就在于，解决了编写代码的人和调用代码的人双方的痛处
		//解放我们的双手
		
	}
}
