package com.effective.section3.demo8_12;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        for (int i = 0; i <= 3; i++){
            set.add(new Demo1(i,i));           
        }
        System.out.println(set);
        set.add(new Demo1(1,1));
        System.out.println(set);
        System.out.println("============");
        System.out.println(set.contains(new Demo1(0,0)));
        System.out.println(set.add(new Demo1(1,1)));
        System.out.println(set.add(new Demo1(4,4)));
        System.out.println("============");
        System.out.println(set);
    }

    private static class Demo1 {
        private int value;
       
        private Integer id;

        public Demo1(int value,int id) {
            this.value = value;
            this.id=id;
        }

        public String toString() {
            return " value = " + value;
        }
        //如果把重写的equal注释了，那么只有自身跟自身比较才返回真，所以，它一直返回假，就是即value使值相同，还是会继续加入
        public boolean equals(Object o) {
            Demo1 a = (Demo1) o;
            return (a.value == value) ? true : false;
        }

        public int hashCode() {
            return id.hashCode();
        }
    }
}

