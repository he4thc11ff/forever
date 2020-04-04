package com.pattern.factory;

import com.pattern.factory.bean.Car;
import com.pattern.factory.simple.SimpleFactory;

public class TestSimpleFactory {
	
	public static void main(String[] args) {
		//简单工厂
		//问题：工厂太强大，维护成本高
		Car car = new SimpleFactory().getCar("Audi");
		System.out.println(car.getName());
		
	}
	
}
