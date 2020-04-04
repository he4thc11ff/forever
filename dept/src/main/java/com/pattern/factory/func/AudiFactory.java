package com.pattern.factory.func;

import com.pattern.factory.bean.Audi;
import com.pattern.factory.bean.Car;

public class AudiFactory implements Factory {

	@Override
	public Car getCar() {
		return new Audi();
	}

}
