package com.effective.section2.demo6;

public class Test {
	public static void main(String[] args) {
		Stack stack=new Stack();
		int a=1;
		int b=3;
		int c=2;
		stack.push(a);
		stack.push(b);
		stack.push(c);
		
		stack.pop();
		Object obj = stack.pop();
		System.out.println(obj);
	}
}
