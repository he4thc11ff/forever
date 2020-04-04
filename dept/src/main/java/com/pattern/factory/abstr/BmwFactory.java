package com.pattern.factory.abstr;

import com.pattern.factory.bean.Bmw;
import com.pattern.factory.bean.Car;

public class BmwFactory extends AbstractFactory {

	@Override
	public Car getCar() {
		return new Bmw();
	}

}
