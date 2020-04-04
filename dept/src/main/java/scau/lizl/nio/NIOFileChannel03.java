package scau.lizl.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        while (true) {
            byteBuffer.clear(); //清空buffer 其实是重置标志位
            // 读完之后 position = limit 若不重置 position = 0
            // 则下次 int read = fileChannel01.read(byteBuffer); 的时候
            // position = limit 则 read = 0 而不是 -1

            int read = fileChannel01.read(byteBuffer);
            System.out.println("read = " + read);
            if (read != -1) {
                byteBuffer.flip();
                fileChannel02.write(byteBuffer);
            } else { // 表示读完毕
                break;
            }
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
