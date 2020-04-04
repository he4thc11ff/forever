package com.pattern.factory;

import com.pattern.factory.func.AudiFactory;
import com.pattern.factory.func.BmwFactory;
import com.pattern.factory.func.Factory;

public class TestFunctionFactory {

	
	
	public static void main(String[] args) {
	
		//工厂方法模式
		//各个产品的生产商，都拥有各自的工厂
		//生产工艺，生成的高科技程度都是不一样的
		Factory factory = new AudiFactory();
		System.out.println(factory.getCar());
		
		//需要用户关心，这个产品的生产商
		factory = new BmwFactory();
		System.out.println(factory.getCar());
		
		//问题：增加了代码的使用复杂度
		
		
	}
	
}
