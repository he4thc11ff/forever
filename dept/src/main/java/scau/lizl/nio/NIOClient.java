package scau.lizl.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws Exception {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务器端的ip和端口
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);

        // 连接服务器
        if (!socketChannel.connect(socketAddress)) {
            while (!socketChannel.finishConnect()) { // socketChannel.connect 为非阻塞
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作");
            }
        }

        // 如果连接成功，则发送数据
        String str = "hello, Heathcliff";
        // buffer 大小与 str 大小一样
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());

        // 发送数据，将 buffer数据写入channel
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
