package com.effective.section6.demo30_31;

enum Color {
	RED(3), BLUE(5), BLACK(8), YELLOW(13), GREEN(28);
	
	private int colorValue;//用自己写的值来标记枚举的唯一值（31.用实力域代替序数）
	
	private Color(int rv) {//enum的构造函数
		this.colorValue = rv;
	}

	public int getColorValue() {
		return colorValue;
	}

	public int value() {//idx索引
		return ordinal() + 1;//大多数人不需要用到ordinal这个方法，也不能把这个当做枚举的值
	}
}
