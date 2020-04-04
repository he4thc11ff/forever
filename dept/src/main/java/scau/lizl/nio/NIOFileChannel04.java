package scau.lizl.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {

        // 创建相关流
        FileInputStream fileInputStream = new FileInputStream("Index.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("Index_copy.jpg");

        // 获取各个流对应的fileChannel
        FileChannel source = fileInputStream.getChannel();
        FileChannel sink = fileOutputStream.getChannel();

        // 使用 transferFrom 完成拷贝
        sink.transferFrom(source, 0, source.size());

        source.close();
        sink.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
