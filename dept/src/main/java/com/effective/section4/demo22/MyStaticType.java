package com.effective.section4.demo22;

public class MyStaticType {

	private static int firstData;
	private int secondData;
	
	private static int getFirstData(){
		return firstData;
	}
	private int getSecondData(){
		return secondData;
	}
	
	/**
	 * (1)然而静态成员变量就无法持有外围类的引用。只能获取外围类的static的成员变量和方法。
	 * (2)静态成员类能够脱离外围实例独立存在。
	 * 	MyType.Style style = new MyType.Style();
	 * @author Administrator
	 *
	 */
	public static class Style{
		public void getStyle(){
			int first = firstData;
			int f = getFirstData();
//			int second = secondData;//报错
//			MyType myType = MyType.this;//报错
		}
	}

}
