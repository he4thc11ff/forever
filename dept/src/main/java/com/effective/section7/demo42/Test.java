package com.effective.section7.demo42;

import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		String[] strs={"to", "too", "two"};
		List<String> homophones = Arrays.asList(strs);
		System.out.println(homophones);
		System.out.println(strs);
		
		int[] digits = { 1, 2, 3, 4, 5 };
		List<int[]> list=Arrays.asList(digits);
		System.out.println(list);
		System.out.println(digits);
		
		Integer[] digits2 = { 1, 2, 3, 4, 5 };
		List<Integer> list2=Arrays.asList(digits2);
		System.out.println(list2);

	}
}
