package com.pattern.factory.abstr;

import com.pattern.factory.bean.Audi;
import com.pattern.factory.bean.Car;

//业务代码具体实现
public class AudiFactory extends AbstractFactory {

	@Override
	public Car getCar() {
		return new Audi();
	}

}
