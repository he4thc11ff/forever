package scau.lizl.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws Exception{
        // 创建ServerSocketChannel 类似 BIO的 ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 得到一个Selector对象
        Selector selector = Selector.open();

        // 绑定一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞，serverSocketChannel 和 socketChannel 都要设置，否则会抛 java.nio.channels.IllegalBlockingModeException
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel 注册到selector，关注的事件是 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true) {
            // 等待一秒，看看有木有事件(连接事件)，若无则返回
            if (selector.select(1000) == 0) { // 没有事件发生
                System.out.println("服务器白给了一秒，无连接");
                continue;
            }

            // 如果返回的>0，就获取到相关的SelectionKey集合
            // 1.如果返回的>0 表示已经获取到关注的事件
            // 2.通过selector.selectedKeys() 获取事件的集合
            // 3.同过selectionKeys 反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 遍历Set<SelectionKey>，使用迭代器
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                // 获取到SelectionKey
                SelectionKey selectionKey = keyIterator.next();
                // 根据key对应的通道发生的事件做相应的处理
                if (selectionKey.isAcceptable()) { // 如果是OP_ACCEPT 有新的客户端连接
                    // 给该客户端生成一个SocketChannel
                    // 传统Server的accept是阻塞的，这里的 serverSocketChannel.accept() 也是阻塞的
                    // 不过由于isAcceptable()，所以可以认为是非阻塞的操作（代码层面上人工保证）
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功，生成了一个socketChannel " + socketChannel.hashCode());
                    // 将当前socketChannel 注册到selector，关注事件为OP_READ，同时给该socketChannel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (selectionKey.isReadable()) { // 发生OP_READ
                    // 通过key反向获取对应的channel 返回SelectableChannel 转成 SocketChannel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取该channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端：" + new String(byteBuffer.array()));
                }

                // 手动从集合中移除当前的selectionKey（处理完了，防止重复操作）
                keyIterator.remove();
            }
        }
    }
}
