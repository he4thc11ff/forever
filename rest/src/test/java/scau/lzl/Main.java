package scau.lzl;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private final static AtomicLong id = new AtomicLong(1);

    public static void main(String[] args) {


        System.out.println(id.getAndIncrement());
        System.out.println(id.getAndIncrement());
        System.out.println(id.getAndIncrement());
        System.out.println(id.getAndIncrement());
    }
}
