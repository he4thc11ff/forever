package scau.lizl.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String str = "hello, 李梓亮"; // UTF-8 英文占1个字节、中文占3个字节
        // 创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\idea-workspace\\helloNetty\\src" +
                "\\main\\resources\\file01.txt");

        // 通过 fileOutputStream 获取对应的 FileChannel
        // fileChannel 的真是类型是 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将 str 放入到 byteBuffer
        byteBuffer.put(str.getBytes());

        // 对byteBuffer 进行flip
        byteBuffer.flip();

        // 将 byteBuffer 的数据写入到 fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
