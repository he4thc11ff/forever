package com.effective.section5.demo25;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test2() {
//		List<String>[] stringLists=new List<String>[1];//创建一个泛型数组，假设合法
//        List<Integer> intList= Arrays.asList(42);//创建并初始化一个包含单个元素的List<Integer>
//        Object[] objects=stringLists;//将List<String>数组保存到一个Object数组变量中，这是合法的，因为数组和协变的。
//        objects[0]=intList;//将List<Integer>保存到Object数组里唯一的元素中，这是可以的，因为泛型是通过擦除实现的。
//        String s=stringLists[0].get(0);//我们从这个数组里唯一的列表中获取唯一的元素，编译器会自动地获取到元素转换成String，但它是一个Integer，因此，我们在运行时得到一个ClassCastException。
//        //为了防止这种情况(创建泛型数组)，第一行就产生了一个编译时错误。
        
        
        List<?>[] stringLists2=new List<?>[1];//创建无限制通配类型的数组是合法的。
		
	}

	private static void test1() {
        Object[] obs=new Long[1];
        obs[0]="asdadasda";
        
//        List<Object> list=new ArrayList<Long>();//编译错误
//        list.add("212asdasda");

		
	}
}
