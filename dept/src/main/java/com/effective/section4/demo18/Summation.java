package com.effective.section4.demo18;

import java.util.List;

//假设有一个接口，它可以实现一组对象的求和，代码如下：
public interface Summation<T> {
	  //实现两个对象的相加
	  T towEleAdd(T obj01, T obj02);

	  //实现List求和
	  T listEleSum(List<T> list);

	  //实现数组求和
	  T arrayEleSum(T[] array);
}
