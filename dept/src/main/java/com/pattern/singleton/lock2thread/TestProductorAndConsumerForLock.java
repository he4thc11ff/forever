package com.pattern.singleton.lock2thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumerForLock {
    public static void main(String[] args) {
        Clerk2 clerk=new Clerk2();

        Productor2 pro=new Productor2(clerk);
        Consumer2 cus=new Consumer2(clerk);

        new Thread(pro,"生产者 A").start();
        new Thread(cus,"消费者 B").start();
        new Thread(pro,"生产者 C").start();
        new Thread(cus,"消费者 D").start();
    }
}

//店员 假如只有一个商品位置
class Clerk2{
    private int product=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    //进货
    public void get(){
        lock.lock();

        try{
            while(product>=1){//为了避免虚假唤醒问题，应该总是使用在循环中
                System.out.println("产品已满！");
                try{
                    condition.await();//this.wait();
                }catch(InterruptedException e){
                }
            }
            System.out.println(Thread.currentThread().getName()+" : "+ ++product);
            condition.signalAll();//this.notifyAll();
        }finally{
            lock.unlock();
        }
    }

    //卖货
    public void sale(){
        lock.lock();

        try{
            while(product<=0){
                System.out.println("缺货！");
                try {
                    condition.await();//this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" : "+ --product);
            condition.signalAll();//this.notifyAll();
        }finally{
            lock.unlock();
        }
    }
}

//生产者
class Productor2 implements Runnable{
    private Clerk2 clerk;
    public Productor2(Clerk2 clerk){
        this.clerk=clerk;
    }
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){
            }
            clerk.get();
        }
    }
}

//消费者
class Consumer2 implements Runnable{
    private Clerk2 clerk;
    public Consumer2(Clerk2 clerk){
        this.clerk=clerk;
    }
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            clerk.sale();
        }
    }
}