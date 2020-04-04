package scau.lizl.nio;

import java.nio.ByteBuffer;

public class NIOByteBufferPutGet {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        byteBuffer.putInt(1); // 4个字节
        byteBuffer.putChar('a'); // 2个字节
        byteBuffer.putChar('啊'); // 2个字节

        byteBuffer.flip();

        // 得到一个只读Buffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        System.out.println(byteBuffer.getInt());
        System.out.println(readOnlyBuffer.getInt());
        System.out.println(readOnlyBuffer.getChar());

        readOnlyBuffer.put((byte) 1);
    }
}
