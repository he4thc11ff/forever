package com.effective.section8.demo48;

import java.math.BigDecimal;

public class Test {
	public static void main(String[] args) {
		//假设有1.03元,花掉0.42元后
		System.out.println(1.03 - .42);//0.6100000000000001
		System.out.println(1.00 - 9 * .10);//0.09999999999999998
		System.out.println("=========");
	    test1();
	    System.out.println("=========");
	    test2();
	    System.out.println("=========");
	    /**
	     * 使用BigDecimal的缺点是：1.与基本类型相比，不方便（需要创建BigDecimal对象）；2.速度慢
			使用int或者long，取决于涉及的数值大小，同时要自己处理十进制小数，以分为单位，而不是以元为单位计算，就可以使用int来处理：
	     */
	    test3();
	}
	private static void test1() {
		// TODO Auto-generated method stub
		double funds = 1.00;
	    int itemsBought = 0;
	    for(double price = .10; funds >= price; price += .10) {
	        funds -= price;
	        itemsBought++;
	        System.out.println("price："+price+"funds："+funds);
	    }
	    System.out.println(itemsBought + " items bought.");
	    System.out.println("Money left over: $" + funds);
	}
	
	private static void test2() {
	    final BigDecimal TEN_CENTS = new BigDecimal(".10");
	    int itemsBought = 0;
	    BigDecimal funds = new BigDecimal("1.00");
	    for(BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
	        itemsBought++;
	        funds = funds.subtract(price);
	        System.out.println("price："+price+"funds："+funds);
	    }
	    System.out.println(itemsBought + " items bought.");
	    System.out.println("Money left over: $" + funds);
		
	}


	private static void test3() {
		// TODO Auto-generated method stub
	    int itemsBought = 0;
	    int funds = 100;
	    for(int price = 10; funds >= price; price += 10) {
	        itemsBought++;
	        funds -= price;
	        System.out.println("price："+price+"funds："+funds);
	    }
	    System.out.println(itemsBought + " items bought.");
	    System.out.println("Money left over: $" + funds);
	}


}
