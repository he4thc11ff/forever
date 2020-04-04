/**
 * 
 */
package com.effective.section3.demo8_12;

import java.util.TreeSet;

/**
 * @author Administrator
 *
 */
public class TreeSetDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//自动排序+去重
		TreeSet<Integer> intSet=new TreeSet<Integer>();
		
		intSet.add(12);
		intSet.add(102);
		intSet.add(33);
		intSet.add(3);
		intSet.add(98);
		intSet.add(3);
		
		//System.out.println(intSet.size());
		
		for(int i: intSet)
		  System.out.println(i);

	}

}
