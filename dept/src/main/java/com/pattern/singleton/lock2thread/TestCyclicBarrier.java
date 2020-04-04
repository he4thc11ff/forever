package com.pattern.singleton.lock2thread;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + "TaskThread 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + "TaskThread2 冲破栅栏 A");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class TaskThread2 extends Thread {

        CyclicBarrier barrier;

        public TaskThread2(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(getName() + "TaskThread2 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + "TaskThread2 冲破栅栏 A");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 设置x个线程都到达后才能冲破栅栏
        int threadNum = 2;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });

//        for(int i = 0; i < threadNum; i++) {
//            new TaskThread(barrier).start();
//        }

        new TaskThread(barrier).start();
        new TaskThread2(barrier).start();
    }

}
