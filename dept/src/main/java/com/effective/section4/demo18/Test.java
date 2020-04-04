package com.effective.section4.demo18;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		SummationImpl obj=new SummationImpl();
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(2);
		Object ret = obj.listEleSum(list);
		System.out.println(ret);
	}
}
