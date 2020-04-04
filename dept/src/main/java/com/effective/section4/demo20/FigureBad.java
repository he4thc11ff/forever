package com.effective.section4.demo20;

/**
 * 破坏了可读性
 * 增加了内存的占用
 * 域不能做成final的
 * 标签类过于冗长、容易出错，并且效率低下
 * @author Administrator
 *
 */
//下面的类，它能够表示圆形或者矩形。
public class FigureBad {

	enum Shape {
		RECTANGLE, CIRCLE//标签
	};

	// Tag field - the shape of this figure
	final Shape shape;

	// These fields are used only if shape is RECTANGLE
	double length;
	double width;

	// This field is used only if shape is CIRCLE
	double radius;

	// Constructor for circle
	FigureBad(double redius) {
		shape = Shape.CIRCLE;
		this.radius = radius;
	}

	// Constructor for rectangle
	public FigureBad(double lenght, double width) {
		shape = Shape.RECTANGLE;
		this.length = lenght;
		this.width = width;
	}

	double area() {
		switch (shape) {
		case RECTANGLE:
			return length * width;
		case CIRCLE:
			return Math.PI * (radius * radius);
		default:
			throw new AssertionError();
		}

	}
}
