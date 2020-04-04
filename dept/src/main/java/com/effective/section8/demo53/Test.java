package com.effective.section8.demo53;

import java.util.Arrays;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		String className = "java.util.HashSet";// [bb, ff, a, c, d, f, zs]
		// String className="java.util.TreeSet";//[a, bb, c, d, f, ff, zs]
		String[] dataArr = { "a", "c", "a", "f", "d", "bb", "zs", "ff" };
		Class<?> c = null;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		}
		Set<String> s = null;
		try {
			s = (Set<String>) c.newInstance();
		} catch (IllegalAccessException e) {
			System.out.println("Class not accessible");
		} catch (InstantiationException e) {
			System.out.println("Class not instantiable");
		}
		s.addAll(Arrays.asList(dataArr));
		System.out.println(s);
	}
}
