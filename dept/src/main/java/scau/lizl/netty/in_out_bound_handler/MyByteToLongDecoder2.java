package scau.lizl.netty.in_out_bound_handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder2.decode() 被调用");

        // ReplayingDecoder 不需要判断数据是否足够读取，内部已经处理了
        out.add(in.readLong());
    }
}
