package scau.lizl.netty.in_out_bound_handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     *  decode 会根据接收到的数据，被调用多次，知道确定没有新的元素被添加到List，
     *  或者是ByteBuf 没有更多的可读字节为止
     *  如果list out 不为空，就会将list的元素一个一个传递给下一个channelInboundHandler处理，该处理器的方法也会调用多次
     *
     * @param ctx 上下文对象
     * @param in 入站的ByteBuf
     * @param out List集合，将解码后的数据传给下一个handler(Inbound)
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder.decode() 被调用");

        // 业务要求：接收Long类型数据(Long占8个字节)
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
