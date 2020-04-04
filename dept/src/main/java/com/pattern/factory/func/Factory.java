package com.pattern.factory.func;

import com.pattern.factory.bean.Car;

//工厂接口，就定义了所有工厂的执行标准
public interface Factory {

	Car getCar();
	
}
