package com.pattern.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		//new MyList().sort(new NumberComparator());
		
		
		//策略模式
		List<Long> numbers = new ArrayList<Long>();
		numbers.add(1L);
		numbers.add(3L);
		numbers.add(2L);
		
		Collections.sort(numbers, new Comparator<Long>() {
			@Override
			//返回值是固定的
			//0 、-1 、1
			//0 、 >0 、<0
			public int compare(Long o1, Long o2) {
				//中间逻辑是不一样的
				if(o1>o2){
					return 1;
				}else if(o1<o2){
					return -1;
				}
				return 0;
			}
		});
		
		System.out.println(numbers.toString());
	}
}
