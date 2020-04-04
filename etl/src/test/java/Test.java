import org.apache.kafka.common.utils.CopyOnWriteMap;

import java.util.concurrent.ConcurrentMap;

public class Test {
    public static void main(String[] args) {

        ConcurrentMap<Integer, String> batches = new CopyOnWriteMap<>();

        String a1 = batches.putIfAbsent(1, "a");

        System.out.println(a1); // null

        String a2 = batches.putIfAbsent(1, "aa");

        System.out.println(a2); // a

        String a3 = batches.putIfAbsent(1, "aaa");

        System.out.println(a3); // a
    }
}
