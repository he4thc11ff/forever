package com.effective.section5.demo23;

import java.util.HashSet;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		//泛型
		MyList<String> myItemMyList = new MyList<String>();
		myItemMyList.add("123");
		myItemMyList.add("1234");
		myItemMyList.add("1235");
		System.out.println(myItemMyList.get(1));
		
		//无限制的通配符类型
		Set<String> set =new HashSet<>();
		set.add("1234");
		set.add("123");
		set.add("1234");
		set.add("1235");
		Set<String> set2 =new HashSet<>();
		set2.add("123");
		set2.add("123");
		set2.add("1234");
		set2.add("1235");
		
		int ret = numElmentsInCommon(set, set2);
		System.out.println(ret);
	}
	// Unbounded wildcard type - typesafe and flexible!
	static int numElmentsInCommon(Set<?> s1, Set<?> s2){
		int result = 0;
		for(Object o1 : s1){
			if(s2.contains(o1)){
				result ++;
			}
		}
		return result;
	}
}
