/**
 * 
 */
package com.effective.section3.demo8_12;

import java.util.HashSet;

/**
 * @author Administrator
 *
 */
public class HashSetDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HashSet<Apple> appleSet=new HashSet<Apple>();
		//每加入一个对象就在内置查询表增加一个他的hashcode返回值，即地址，然后hashcode执行的操作就是每次与这表进行对比
		//如果值不同则接纳这个对象，如果值相同则进行equal比较，不同则接纳
		//问题什么时候hashcode比较相同而equal比较不同？>>>>重写equal或hashode方法，即比较的属性减少
		appleSet.add(new Apple("red",130));
		appleSet.add(new Apple("red",130));
		
		System.out.println(appleSet.size());
		

	}

}
