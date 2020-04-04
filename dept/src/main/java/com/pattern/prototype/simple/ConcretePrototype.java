package com.pattern.prototype.simple;

import java.util.ArrayList;

public class ConcretePrototype implements Cloneable{

	private int age;

	private String name;
	
	public ArrayList<String> list = new ArrayList<String>();
	
	/**
	 * 浅拷贝
	 */
	public Object clone() throws CloneNotSupportedException {
		ConcretePrototype prototype = null;
		try{
			prototype = (ConcretePrototype)super.clone();
			prototype.list = (ArrayList)list.clone();//1不算深拷贝，如果list里面还有对象就不行了
			
			//2这种方式不好，如果成员变量数据类型复杂就要写很多
			//虽然可以用反射，或者循环，但还不是最优
		}catch(Exception e){
			
		}
		
		return prototype;
	}

	
	//定义上100个属性
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
