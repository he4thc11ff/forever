package com.effective.section6.demo30_31;

public class Test {
	
	public static void main(String[] args) {
		testColor();//enum简单测试
		testOperation();//enum应用
	}


	private static void testColor() {
		for (Color s : Color.values()) {
			// enum的values()返回一个数组，这里就是Seasons[]
			System.out.println(s.value() + ":" + s.name() + "=" + s.getColorValue());
		}
		
	}

	private static void testOperation() {
	    double x = 2.0;
	    double y = 4.0;
	    for(Operation op : Operation.values())
	        System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x,y));
		
	}
}
