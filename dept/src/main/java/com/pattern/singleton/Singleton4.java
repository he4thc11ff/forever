package com.pattern.singleton;

//懒汉式（静态内部类）
//这种写法，即解决安全问题，又解决了性能问题
//这个代码，没有浪费一个字
public class Singleton4{
	//1、先声明一个静态内部类
	//private 私有的保证别人不能修改
	//static 保证全局唯一
	//这个静态内部类只有一份，因为在类被加载的时候就已经出现了，解决了多线程安全问题
	private static class LazyHolder {
		private static final Singleton4 INSTANCE = new Singleton4();
	}
	//2、将默认构造方法私有化
	//如果不写private相当于有一个默认的public的无参的构造方法，就意味着在代码中随时都可以new出来
	private Singleton4 (){}
		
	//3、同样提供静态方法获取实例
	//final 确保别人不能覆盖
	public static final Singleton4 getInstance() {  
		
		//方法中的逻辑，是要在用户调用的时候才开始执行的
		//方法中实现逻辑需要分配内存，也是调用时才分配的
		return LazyHolder.INSTANCE;
  }
	
//	static int a = 1;
//	//不管该class有没有实例化，static静态块总会在classLoader执行完以后，就加载完毕
//	static{
//		//静态块中的内容，只能访问静态属性和静态方法
//		//只要是静态方法或者属性，直接可以用Class的名字就能点出来
//		Singleton4.a = 2;
//		//JVM 内存中的静态区，这一块的内容是公共的 
//	}
}

//我们所写的所有的代码，在java的反射机制面前，都是裸奔的
//反射机制是可以拿到private修饰的内容的
//我们可以理解成即使加上private也不靠谱（按正常套路出牌，貌似可以）


//类装载到JVM中过程
//1、从上往下(变量必须声明在前，使用在后) 成员变量除外，为什么呢？因为第2点，类装载的时候先属性后方法，属性就是成员变量了
//2、先属性、后方法
//3、先静态、后动态
