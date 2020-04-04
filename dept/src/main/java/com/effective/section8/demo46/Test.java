package com.effective.section8.demo46;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Test {
	enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }
	
	public static void main(String[] args) {
		test1();
		System.out.println("=========");
		test2();
		System.out.println("=========");
		test3();
	}



	private static void test1() {
		Collection<Face> faces = Arrays.asList(Face.values());
		for(Iterator<Face> i = faces.iterator(); i.hasNext();) 
		    for(Iterator<Face> j = faces.iterator(); j.hasNext();) 
		        System.out.println(i.next() + " " + j.next());
	}
	private static void test2() {
		Collection<Face> faces = Arrays.asList(Face.values());
		for(Iterator<Face> i = faces.iterator(); i.hasNext();){
			Face face = i.next();//外层循环的变量放到内层执行太多次next()了
			for(Iterator<Face> j = faces.iterator(); j.hasNext();) 
				System.out.println(face + " " + j.next());
		} 
	}
	private static void test3() {
		//使用foreach比较简洁
		Collection<Face> faces = Arrays.asList(Face.values());
		for(Face face1 : faces)
		    for(Face face2 : faces)
		        System.out.println(face1 + " " + face2);
	}
}
