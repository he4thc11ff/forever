package com.effective.section4.demo21;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
	
	public static void main(String[] args) {
//		test();
		test2();
	}
    public static void test(){
        Student students[]=new Student[10];
        for(int i=0;i<5;i++){
            students[i] = new Student();
        }
        students[0].id = 5; students[0].name="wanghui";
        students[1].id = 3; students[1].name="zhangsan";
        students[2].id = 2; students[2].name="lisi";
        students[3].id = 4; students[3].name="linyifeng";
        students[4].id = 1; students[4].name="houzhenjie";
//        但是注意，以这种方式使用匿名类时，将会在每次执行调用的时候创建一个新的实例。
//        如果它被重复执行，考虑将函数对象存储到一个私有的静态final域里，并重用它。
//        这样做的另一种好处是，可以为这个函数对象取一个有意义的域名
        Arrays.sort(students,0,5,new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id-o2.id;
            }
        });
        for(int i=0;i<5;i++){
            System.out.println(students[i].id+":"+students[i].name);
        }
    }
    public static void test2(){
    	Student students[]=new Student[10];
    	for(int i=0;i<5;i++){
    		students[i] = new Student();
    	}
    	students[0].id = 5; students[0].name="wanghui";
    	students[1].id = 3; students[1].name="zhangsan";
    	students[2].id = 2; students[2].name="lisi";
    	students[3].id = 4; students[3].name="linyifeng";
    	students[4].id = 1; students[4].name="houzhenjie";
    	
    	Arrays.sort(students,0,5, Host.STUDENT_COMPARATOR);
    	for(int i=0;i<5;i++){
    		System.out.println(students[i].id+":"+students[i].name);
    	}
    }
}
