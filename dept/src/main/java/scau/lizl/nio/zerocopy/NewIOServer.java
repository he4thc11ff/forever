package scau.lizl.nio.zerocopy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {
    public static void main(String[] args) throws Exception{
        InetSocketAddress address = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        long total = 0;

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            long readCount = 0;
            while (-1 != readCount) {
                total += readCount;
                try {
                    readCount = socketChannel.read(byteBuffer);
                } catch (Exception e) {
                    break;
                }
                byteBuffer.rewind(); // 倒带，position变成0 mark标志作废
            }

            System.out.println(total);
        }
    }
}
