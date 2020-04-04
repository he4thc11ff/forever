package scau.lizl.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder.decode() 被调用");

        if (in.readableBytes() >= 4) { // MessageProtocol.len 的类型是 int 占4个字节
            int length = in.readInt();
            if (in.readableBytes() >= length) { // 根据content大小来读取数据
                byte[] content = new byte[length];
                in.readBytes(content);

                // 封装成MessageProtocol对象，add到list，交给下一个handler处理
                MessageProtocol messageProtocol = new MessageProtocol();
                messageProtocol.setLen(length);
                messageProtocol.setContent(content);

                out.add(messageProtocol);
            }
        }
    }
}

//public class MyMessageDecoder extends ReplayingDecoder<Void> {
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        System.out.println("MyMessageDecoder.decode() 被调用");
//
//        int length = in.readInt();
//        byte[] content = new byte[length];
//        in.readBytes(content);
//
//        // 封装成MessageProtocol对象，add到list，交给下一个handler处理
//        MessageProtocol messageProtocol = new MessageProtocol();
//        messageProtocol.setLen(length);
//        messageProtocol.setContent(content);
//
//        out.add(messageProtocol);
//    }
//}
