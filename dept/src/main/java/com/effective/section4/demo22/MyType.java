package com.effective.section4.demo22;

public class MyType {

	private int firstData;
	private int secondData;
	
	private int getFirstData(){
		return firstData;
	}
	private int getSecondData(){
		return secondData;
	}
	
	/**
	 * (1)非静态成员类隐含持有外部类的引用，通过XXClass.this来获取外部类的引用。也就是说能够获取外部类的所用成员变量与成员方法
	 * (2)普通成员类就必须先创建外围实例。
		MyType.Style style = new MyType().new Style();
	 * @author Administrator
	 *
	 */
	public class Style{
		
		public void getStyle(){
			//获取外围类成员变量
			int first = firstData;
			//获取外围了的方法
			int second = getFirstData();
			//获取外围了的引用
			MyType type = MyType.this;
		}
	}

}
