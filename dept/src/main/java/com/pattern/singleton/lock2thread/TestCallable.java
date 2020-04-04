package com.pattern.singleton.lock2thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args) {
        ThreadDemo2 td = new ThreadDemo2();

        //1. 执行Callable方式，需要FutureTask实现类的支持，用于接收运行结果
        FutureTask<Integer> futureTask = new FutureTask<>(td);
        new Thread(futureTask).start();

        //2. 接收线程运算后的结果
        Integer result = null;
        try {
            while (!futureTask.isDone()) {
                System.out.println("wait...");
            }
            result = futureTask.get(); // 主线程在这里阻塞，等待thread执行完成
            System.out.println(result);
            System.out.println("----------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class ThreadDemo2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }

        return sum;
    }
}
