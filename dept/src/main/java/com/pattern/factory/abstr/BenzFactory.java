package com.pattern.factory.abstr;

import com.pattern.factory.bean.Benz;
import com.pattern.factory.bean.Car;

public class BenzFactory extends AbstractFactory {

	@Override
	public Car getCar() {
		return new Benz();
	}

}
