package com.effective.section4.demo18;

import java.util.List;

/**
 * 骨架类
 * @author Administrator
 * 根据观察，它的基本方法只有一个T towEleAdd(T obj01, T obj02);,现在我们可以来实现他的“骨架”了：
 * @param <T>
 */

public abstract class AbstractSummation<T> implements Summation<T> {
	  @Override
	  public abstract T towEleAdd(T obj01, T obj02);

	  @Override
	  public T listEleSum(List<T> list) {
	    T firstEle = null;
	    for (T t : list) {

	      if (firstEle == null) {
	        firstEle = t;
	        continue;
	      }

	      firstEle = towEleAdd(firstEle, t);
	    }
	    return firstEle;
	  }

	  @Override
	  public T arrayEleSum(T[] array) {
	    T firstEle = null;
	    for (T t : array) {

	      if (firstEle == null) {
	        firstEle = t;
	        continue;
	      }

	      firstEle = towEleAdd(firstEle, t);
	    }
	    return firstEle;
	  }
}
