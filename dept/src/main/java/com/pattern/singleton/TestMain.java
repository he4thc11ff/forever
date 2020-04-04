package com.pattern.singleton;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class TestMain {
    public static void main(String[] args) throws Exception{  
//        isOnly();
        testThread();
    }
    
    /**
     * 多线程测试
     */
    private static void testThread() {
		//启动100线程同时去抢CPU
//		int count = 100;
		int count = 100000;

		//发令枪，测试并发经常用到
		CountDownLatch latch = new CountDownLatch(count);
		//Set默认去重的，set是本身线程不安全的
		//
		final Set<Singleton4> syncSet = Collections.synchronizedSet(new HashSet<Singleton4>());
		
		for (int i = 0; i < count; i++) {
			new Thread(){
				@Override
				public void run() {
					syncSet.add(Singleton4.getInstance());
				}
			}.start();
			
			latch.countDown();
		}
		  
		try {
			latch.await();//等待所有线程全部完成，最终输出结果
			System.out.println("取到了多少个实例："+syncSet.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	/**
     * 单例会被反射与序列化破坏
     * @throws Exception
     */
	private static void isOnly() throws Exception {
	    //为了便于理解，忽略关闭流操作及删除文件操作。真正编码时千万不要忘记
	    //Exception直接抛出
        //Write Obj to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(Singleton0.INSTANCE);
        //Read Obj from file
        File file = new File("tempFile");
        ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
        Singleton0 newInstance = (Singleton0) ois.readObject();
        //判断是否是同一个对象
        System.out.println(newInstance == Singleton0.INSTANCE);
		
	}
}