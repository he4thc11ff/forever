package com.effective.section7.demo41;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetList {

	public static void main(String[] args) {
		
		Set<Integer> set = new TreeSet<Integer>();
	    List<Integer> list = new ArrayList<Integer>();
	    
	    for(int i=-3; i<3; i++)
	    {
	    	//自动装箱
	    	set.add(i);
	    	list.add(i);
	    }
	    for(int i=0; i<3; i++)
	    {
	    	set.remove(i);//把对象1 2 3移除
//	    	list.remove(i);//把索引1 2 3移除（我们的目的是移除对象，看源码得知是重载的问题！！！）
	    	list.remove(Integer.valueOf(i));//把对象1 2 3移除
	    }
	    System.out.println("set : "+set);
	    System.out.println("list : "+list);
	}

}
