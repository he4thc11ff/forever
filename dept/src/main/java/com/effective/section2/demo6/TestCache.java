package com.effective.section2.demo6;

public class TestCache {
	public static void main(String[] args) {
		
		/**
		 * 源码中有eden和longterm的两个map，对jvm堆区有所了解的话，可以猜测出tomcat在这里是使用
		 * ConcurrentHashMap和WeakHashMap做了分代的缓存。在put方法里，在插入一个k-v时，
		 * 先检查eden缓存的容量是不是超了。没有超就直接放入eden缓存，如果超了则锁定longterm
		 * 将eden中所有的k-v都放入longterm。再将eden清空并插入k-v。在get方法中，也是优先从eden
		 * 中找对应的v，如果没有则进入longterm缓存中查找，找到后就加入eden缓存并返回。 
		 */
		/**
		 * 经过这样的设计，相对常用的对象都能在eden缓存中找到，不常用（有可能被销毁的对象）的
		 * 则进入longterm缓存。而longterm的key的实际对象没有其他引用指向它时，gc就会自动回收
		 * heap中该弱引用指向的实际对象，弱引用进入引用队列。longterm调用expungeStaleEntries()
		 * 方法，遍历引用队列中的弱引用，并清除对应的Entry，不会造成内存空间的浪费。
		 */
		ConcurrentCache<Integer, String> cache = new ConcurrentCache<>(3);
		for (int i = 0; i < 10; i++) {
			cache.put(i, "v"+i);
		}
		
		String obj = cache.get(1);
		System.out.println(obj);
		
	}
}
