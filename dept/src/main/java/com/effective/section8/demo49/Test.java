package com.effective.section8.demo49;

public class Test {
	public static void main(String[] args) {
		test1();
//		test2();
		test3();
	}

	/**
	 * 如果first和second引用表示同一个int值得不同Integer实例，这个比较操作会返回false
	 */
	private static void test1() {
		Integer first=new Integer(1);
		Integer second=new Integer(1);
		System.out.println(first==second);//false
		System.out.println(first.equals(second));//true
		
	}
	
	/**
	 *它不是打印出Unbelievable，而是抛出NullPointException异常，问题在于i是个Integer，不是int，它的初始值是null而不是0，
	 *当计算（i == 42）时，将Integer和int进行比较，装箱基本类型自动拆箱，如果null对象被自动拆箱，就只能得到Nu'llPointException了。
	 */
	static Integer i;
	private static void test2() {
	    if (i == 42) {
	    	System.out.println("Unbelievable");
	    }
	}
	private static void test3() {
		{
			/**
			 * 程序的运行比较慢，原因在于把局部变量sum声明为装箱基本类型Long，而不是基本类型long，程序运行完全没问题，只是会导致明显的性能下降，因为变量被反复地进行装箱和拆箱。
			 */
			long begintime=System.currentTimeMillis();
			Long sum = 0L;
			for(long i = 0; i < Integer.MAX_VALUE; i++) {
				sum += i;
			}
			System.out.println("consume="+(System.currentTimeMillis()-begintime)+"ms;sum="+sum);//consume=23480ms;sum=2305843005992468481
		}
	    {
	    	long begintime=System.currentTimeMillis();
	    	long sum = 0L;
	    	for(long i = 0; i < Integer.MAX_VALUE; i++) {
	    		sum += i;
	    	}
	    	System.out.println("consume="+(System.currentTimeMillis()-begintime)+"ms;sum="+sum);//consume=5552ms;sum=2305843005992468481
	    	
	    }
		
	}
}
