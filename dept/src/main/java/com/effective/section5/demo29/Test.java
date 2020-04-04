package com.effective.section5.demo29;

import java.util.ArrayList;

public class Test {
    public  static void main(String[] args) {
        Favorite f = new Favorite();
        f.add(String.class, "string");//可以插入String类型
        f.add(Integer.class, Integer.valueOf(123));//可以插入int类型
         
        f.add(new ArrayList<String>(), "String");//可以插入String类型
        f.add(new ArrayList<Integer>(), 123);//可以插入int类型
         
        String s1 = f.get(String.class);
        Integer i = f.get(Integer.class);
        System.out.println(s1);
        System.out.println(i);
    }
}
