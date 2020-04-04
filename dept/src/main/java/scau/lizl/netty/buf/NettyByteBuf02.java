package scau.lizl.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8);

        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();

//            System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
            System.out.println(new String(content, CharsetUtil.UTF_8));

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readableBytes());
            System.out.println(byteBuf.readByte()); // h 对应的ascii码 104
            System.out.println((char) byteBuf.readByte()); // h 对应的ascii码 104
            System.out.println(byteBuf.readableBytes());
        }
    }
}
