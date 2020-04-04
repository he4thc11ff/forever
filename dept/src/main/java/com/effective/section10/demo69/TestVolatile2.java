package com.effective.section10.demo69;

public class TestVolatile2 {

    public static boolean flag;

    public static void main(String[] args) {

        flag = true;
        ThreadDemo2 threadDemo2 = new ThreadDemo2();

        new Thread(threadDemo2).start();
        while (true) {
            if (!flag) {
                System.out.println("----");
                break;
            }
        }
    }

}

class ThreadDemo2 implements Runnable{
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestVolatile2.flag=false;
        System.out.println("flag="+ TestVolatile2.flag);
    }
}


