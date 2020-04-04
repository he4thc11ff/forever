package com.effective.section10.demo66;

import java.util.concurrent.TimeUnit;


public class StopThread1 {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                System.out.println(stopRequested); //false
                //这里停不下来是因为主线程对stopRequest进行修改的时候，这个线程并不可见
                while (!stopRequested) {
                    ++i;
                    System.out.println(i);
                }
            }
        });
        //启动线程
        backgroundThread.start();

        //休眠1秒
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
        System.out.println("stopRequested="+stopRequested);
    }

}
