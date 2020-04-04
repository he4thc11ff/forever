package com.pattern.factory.func;

import com.pattern.factory.bean.Bmw;
import com.pattern.factory.bean.Car;

public class BmwFactory implements Factory {

	@Override
	public Car getCar() {
		return new Bmw();
	}

}
