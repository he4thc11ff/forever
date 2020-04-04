package scau.lizl.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));

        String filename = "protoc-3.6.1-win32.zip";
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        // 准备发送
        long startTime = System.currentTimeMillis();

        // 在Linux下 一次transferTo 就可以完成传输
        // 在Windows下 一次transferTo 只能发送8M文件，需要分段传输文件
        // 注意传输时的位置
        // transferTo 底层就是零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总字节数：" + transferCount + "，耗时：" + (System.currentTimeMillis() - startTime));

        // 关闭
        fileChannel.close();
    }
}
