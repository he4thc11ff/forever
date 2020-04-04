package com.effective.section10.demo66;

import java.util.concurrent.TimeUnit;


public class StopThread2 {
    /**
     * 停止线程变量
     */
    private static boolean stopRequested;

    //吧对变量的读和写方法都进行同步
    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }


    public static void test2() {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested()) {
                    ++i;
                    System.out.println("test2-"+i+";stopRequested="+stopRequested());
                }
            }
        });
        //启动线程
        backgroundThread.start();

        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        requestStop();
        System.out.println("requestStop()");
    }



    public static void main(String[] args) throws InterruptedException {
        test2();
    }

}
