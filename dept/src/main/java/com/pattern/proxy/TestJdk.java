package com.pattern.proxy;

import com.pattern.proxy.bean.Boy;
import com.pattern.proxy.bean.Person;
import com.pattern.proxy.jdk.CustMeipo;
import com.pattern.proxy.jdk.Meipo;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class TestJdk {
	//满足代理模式应用场景的三个必要条件，穷取法
	//1、两个角色：执行者、被代理对象
	//2、注重过程，必须要做，被代理对象没时间做或者不想做（怕羞羞），不专业
	//3、执行者必须拿到被代理对象的个人资料（执行者持有被代理对象的引用）

	public static void main(String[] args) {
		
		try {
			//原理：
			//1.拿到被代理对象的引用，然后获取它的接口
			//2.JDK代理重新生成一个类，同时实现我们给的代理对象所实现的接口
			//3.把被代理对象的引用也拿到了
			//4.重新动态生成一个class字节码
			//5.然后编译
//			jdkProxy();
//			getClassContent();
			jdkProxyCust();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用jdk的代理，动态代理对象
	 * @throws Exception
	 */
	private static void jdkProxy() throws Exception {
		Person obj = (Person)new Meipo().getInstance(new Boy());
		System.out.println("代被理对象:"+obj.getClass());
		obj.findLove();
	}
	/**
	 * 获取字节码内容
	 * @throws Exception
	 */
	private static void getClassContent() throws Exception {
		byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
		FileOutputStream os = new FileOutputStream("I:/[5]temp/temp/$Proxy0.class");
		os.write(data);
		os.close();
	}
	/**
	 * 使用自己写的代理，实现动态代理
	 * @throws Exception
	 */
	private static void jdkProxyCust() throws Exception {
		Person obj = (Person)new CustMeipo().getInstance(new Boy());
		System.out.println("代被理对象:"+obj.getClass());
		obj.findLove();
	}
	
	
}

