package com.effective.section4.demo18;

//继承骨架类就只用实现towEleAdd方法，就可以完成一组对象的求和工作了。

//Object ret = obj.listEleSum(list);                                      java 泛型可以指定一个上限，如果不指定，上限默认是 Object
//1、public class SummationImpl extends AbstractSummation -> listEleSum(List list) 实现towEleAdd时默认是Object
//2、public class SummationImpl extends AbstractSummation<Object> -> listEleSum(List<Object> list)

public class SummationImpl extends AbstractSummation {

	@Override
	public Object towEleAdd(Object obj01, Object obj02) {
		if(obj01 instanceof Integer){
			int temp1 = (int)obj01;
			int temp2 = (int)obj02;
			return temp1+temp2;
		}
		return null;
	}



}
