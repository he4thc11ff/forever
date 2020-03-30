package scau.lzl;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private final static AtomicLong id = new AtomicLong(1);

    public static void main(String[] args) {
        // JUC
        System.out.println(id.getAndIncrement());

        // SnowFlake
        long WORKER_ID_BITS = 5L;
        System.out.println("-1L << WORKER_ID_BITS -> " + (-1L << WORKER_ID_BITS));
        System.out.println("~(-1L << WORKER_ID_BITS) -> " + ~(-1L << WORKER_ID_BITS)); // ~ 取反
    }
}
