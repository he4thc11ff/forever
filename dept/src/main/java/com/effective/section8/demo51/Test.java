package com.effective.section8.demo51;

public class Test {
	public static void main(String[] args) {
		long timestrap=System.currentTimeMillis();
		//由于String是final的，不可变，他内部每次拼接都会创建一个StringBuffer对象，这样他创建了5万次对象，性能就比较差了
		String result = "";
		for (int i = 0; i < 10000; i++) {
			result += (i);
		}
		System.out.println("consume:"+(System.currentTimeMillis()-timestrap));//consume:233
		
		timestrap=System.currentTimeMillis();
		//而StringBuilder只在外面创建了一个对象，其他直接append字符串即可
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			sb.append(i);
		}
		System.out.println("consume:"+(System.currentTimeMillis()-timestrap));//consume:1
	}

	
}
