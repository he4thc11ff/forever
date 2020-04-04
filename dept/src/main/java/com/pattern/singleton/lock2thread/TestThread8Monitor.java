package com.pattern.singleton.lock2thread;

/*
 * 实验：观察打印的"one" or "two" ?
 *
 * 1.两个普通同步方法，两个线程，标准打印，打印？  //one two  因为同步锁是this(调用对象本身),被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 * 2.新增Thread.sleep()给 getOne(),打印？ //等3秒后  one two
 * 3.新增普通方法(非同步) getThree(),打印？//three 等3秒 one two 因为同步锁不影响普通方法的执行
 * 4.两个普通同步方法，两个Number对象，打印？//two 等3秒 one  因为用的不是同一把锁，互不影响
 * 5.修改 getOne() 为静态同步方法，使用一个Number对象打印?  //two 等3秒 one  因为静态同步方法用的锁是类对象本身，Number.class; 和对象用的是不同的锁
 * 6.修改两个方法均为静态同步方法，一个Number对象？//等3秒 one two 用的锁都是Number类对象本身
 * 7.一个静态同步方法，一个非静态同步方法，两个Number对象？//two 等3秒one
 * 8.两个静态同步方法，两个Number对象？//等3秒后  one two 用的锁都是Number类对象本身
 *
 * 线程八锁的关键：
 * ①非静态方法的锁默认为  this,  静态方法的锁为 对应的 Class 实例
 * ②某一个时刻内，只能有一个线程持有同一把锁，无论几个方法。
 */

public class TestThread8Monitor {
    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();
        new Thread(new Runnable() {
            public void run() {
                number.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
//              number.getTwo();
                number2.getTwo();
            }
        }).start();
//      new Thread(new Runnable() {
//          public void run() {
//              number.getThree();
//          }
//      }).start();

    }

}

class Number {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }

//  public void getThree(){
//      System.out.println("three");
//  }
}
