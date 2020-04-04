package com.effective.section4.demo22;

public class Test {

	public static void main(String[] args) {
		//匿名类的用法
		//（1）创建函数对象
//		Arrays.sort(stringArray, new Comparator<String>() {  
//		    public int compare(String s1, String s2) {  
//		        return s1.length() - s2.length();  
//		    }  
//		});
		//test
//		Cmp<String> ss = new Cmp<String>() {  
//		    public int compare(String s1, String s2) {  
//		        return s1.length() - s2.length();  
//		    }  
//		};
//		
//		int len = ss.compare("31234234", "231");
//		System.out.println(len);
		
		
//		（2）创建过程对象
		runSomeThing();
		
//		（3）静态工厂方法内部
	}
	
	
	public static void runSomeThing(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("I am running");
            }
        };
        new Thread(runnable).start();
    }
}
