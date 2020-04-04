package com.pattern.factory.func;

import com.pattern.factory.bean.Benz;
import com.pattern.factory.bean.Car;

public class BenzFactory implements Factory {

	@Override
	public Car getCar() {
		return new Benz();
	}

}
