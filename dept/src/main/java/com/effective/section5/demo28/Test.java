package com.effective.section5.demo28;

import java.util.ArrayList;
import java.util.Collection;

public class Test {
	public static void main(String[] args) {
		Stack<Number> numberStack = new Stack<Number>();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		list.add(2);
		
		Iterable<Integer> integers = (Iterable<Integer>) list;
		numberStack.pushAll(integers);
		numberStack.push(10);
		System.out.println("size before="+numberStack.size());
		
		System.out.println("pop="+numberStack.pop());
		System.out.println("pop="+numberStack.pop());
		
		Collection<Number> coll=new ArrayList<>();
		numberStack.popAll(coll);
		System.out.println("size after="+coll.size());
	}
}
