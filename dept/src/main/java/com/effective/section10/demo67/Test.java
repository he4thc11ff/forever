package com.effective.section10.demo67;


import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        //创建一个被观察的对象
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        //添加一个观察者
        set.addObserver(new SetObserver<Integer>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
    }

    public static void test2() {
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        set.addObserver(new SetObserver<Integer>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23) //到23，我们取消这个观察者，但是会爆出异常，因为在迭代遍历列表的时候我们自己修改了列表，这是非法的
                    s.removeObserver(this);
            }
        });

        for (int i = 0; i < 100; i++){
            set.add(i);
        }
    }

    /**
     * 那么如何取消观察者者呢？？？？
     * 我们使用另外的一个线程在23的时候删除这个观察者-->会死锁
     */
    public static void test3() {
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(final ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                //如果是23
                if (element == 23) {
                    //线程池，创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try {
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                //这里会死锁
                                set.removeObserver(observer);
                            }
                        }).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                        executor.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++){
            System.out.println("i="+i);
            set.add(i);
        }
    }
}
