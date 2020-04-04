/**
 * 
 */
package com.effective.section3.demo8_12;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Administrator
 *
 */
public class HashSetDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HashSet<Integer> intSet=new HashSet<Integer>();
		Integer[] arr={5,4,8,100,24,46,123,54,7,12,1,45,1};
//		intSet.add(12);
//		intSet.add(102);
//		intSet.add(33);
//		intSet.add(3);
//		intSet.add(98);
//		intSet.add(3);
		intSet.addAll(Arrays.asList(arr));
		System.out.println(intSet);
		
//		for(Integer i:intSet)
//		  System.out.println(i);

	}

}
