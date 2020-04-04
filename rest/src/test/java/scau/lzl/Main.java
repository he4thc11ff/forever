package scau.lzl;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private final static AtomicLong id = new AtomicLong(1);

    private final static ByteBuffer buffer1 = ByteBuffer.allocate(500 * 1024);
    private final ByteBuffer buffer3 = ByteBuffer.allocate(500 * 1024 * 1024); // 超过Xmx 报OOM

    public static void main(String[] args) throws InterruptedException {
        // JUC
        System.out.println(id.getAndIncrement());

        // SnowFlake
        long WORKER_ID_BITS = 5L;
        System.out.println("-1L << WORKER_ID_BITS -> " + (-1L << WORKER_ID_BITS));
        System.out.println("~(-1L << WORKER_ID_BITS) -> " + ~(-1L << WORKER_ID_BITS)); // ~ 取反

        // memory
        Main main1 = new Main();
    }
}
