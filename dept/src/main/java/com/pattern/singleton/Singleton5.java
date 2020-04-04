package com.pattern.singleton;

import java.util.HashMap;
import java.util.Map;


//类似Spring里面的方法，将类名注册，下次从里面直接获取。  
public class Singleton5 {
	private static Map<String, Singleton5> map = new HashMap<String, Singleton5>();
	static {
		Singleton5 single = new Singleton5();
		map.put(single.getClass().getName(), single);
	}

	// 保护的默认构造子
	protected Singleton5() {
	}

	// 静态工厂方法,返还此类惟一的实例
	public static Singleton5 getInstance(String name) {
		if (name == null) {
			name = Singleton5.class.getName();
		}
		if (map.get(name) == null) {
			try {
				map.put(name, (Singleton5) Class.forName(name).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return map.get(name);
	}
}
