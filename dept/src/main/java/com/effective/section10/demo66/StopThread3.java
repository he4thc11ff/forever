package com.effective.section10.demo66;

import java.util.concurrent.TimeUnit;


public class StopThread3 {

    /**
     * 停止线程变量,这个使用关键字volatile使每个线程都是获取到最新的值
     */
    private static volatile boolean stopRequested;

    public static void test3() {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                System.out.println("这里使用最新的stopRequested2值");
                while (!stopRequested) {
                    ++i;
                    System.out.println("test3"+i+";stopRequested="+stopRequested);
                }
            }
        });

        //启动线程
        backgroundThread.start();

        //停下1s之后执行变量修改程序
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //修改变量
        stopRequested = true;
        System.out.println("stopRequested="+stopRequested);
    }


    public static void main(String[] args) throws InterruptedException {
        test3();
    }
}
