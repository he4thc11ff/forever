/**
 * 
 */
package com.effective.section3.demo8_12;

import java.util.TreeSet;

/**
 * @author Administrator
 *
 */
public class TreeSetDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TreeSet<Apple> appleSet=new TreeSet<Apple>();
		//问题:comerareto的比较次数？
		//采用的是中间开始排序的算法
		appleSet.add(new Apple("red",130));
		appleSet.add(new Apple("red",130));
//
		appleSet.add(new Apple("green",41));
		appleSet.add(new Apple("red",121));
		appleSet.add(new Apple("green",88));
//		appleSet.add(new Apple("red",140));
//		appleSet.add(new Apple("yellow",11));

//		appleSet.add(new Apple("green",20));
//		appleSet.add(new Apple("red",102));
//		appleSet.add(new Apple("green",66));
//		appleSet.add(new Apple("yellow",171));
//		appleSet.add(new Apple("yellow",151));		
		
		for(Apple apple:appleSet)
		  System.out.println(apple);
		

	}

}
