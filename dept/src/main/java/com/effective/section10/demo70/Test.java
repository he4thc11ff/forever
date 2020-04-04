package com.effective.section10.demo70;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 *
 * 这个程序的运行结果是concurrent1和concurrent2随机出现，syn1都在syn2之后或者之前。
 * 由此可说明使用私有对象锁可以避免父子类方法的互相同步的干扰问题。
 * 因此，在Effective Java中说私有对象锁尤其适用于那些专门为继承而设计的类中。
 *
 * 结论：
 * 使用同步方法会造成的子类在无意之中妨碍基类的操作。
 * syn2和concurrent2都想成为一个不干扰父类方法并且也不被父类方法干扰的同步方法。从运行的结果来看，concurrent2做到了，
 * 但syn2没做到。syn2和syn1由于使用synchronized(this)实现同步而对彼此造成干扰。
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch start = new CountDownLatch(1);
        final CyclicBarrier firstSectionOver = new CyclicBarrier(2);
        final Son son = new Son();
        new Thread(new Runnable() {
            public void run() {
                try {
                    start.await();
                    son.syn1();
                    firstSectionOver.await();
                    son.concurrent1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    start.await();
                    son.syn2();
                    firstSectionOver.await();
                    son.concurrent2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        start.countDown();

    }
}

class Parent {
    private final Object parentLock = new Object();

    public synchronized void syn1() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("syn1");
        }
    }

    public void concurrent1() throws InterruptedException {
        synchronized (parentLock) {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("concurrent1");
            }
        }
    }
}

class Son extends Parent {
    private final Object sonLock = new Object();

    public synchronized void syn2() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("syn2,has no concern about the Method syn1");
        }
    }

    public void concurrent2() throws InterruptedException {
        synchronized (sonLock) {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("concurrent2,,has no concern about the Method concurrent1");
            }
        }
    }
}
