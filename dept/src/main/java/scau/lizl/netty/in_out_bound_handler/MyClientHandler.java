package scau.lizl.netty.in_out_bound_handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler.channelActive() 发送数据");
        ctx.writeAndFlush(123456L); // 发送的是一个Long

        // 分析
        // 1.abcdabcdefghabcd 是16个字节
        // 2. 该处理器的前一个handler是 MyLongToByteEncoder
        // 3. MyLongToByteEncoder 的父类是 MessageToByteEncoder
        // 4. MessageToByteEncoder 的write方法如下
        /*
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            ByteBuf buf = null;
            try {
                if (acceptOutboundMessage(msg)) { // 判断当前msg是不是要处理的类型，如果不是，则跳过处理(编码)
                    @SuppressWarnings("unchecked")
                    I cast = (I) msg;
                    buf = allocateBuffer(ctx, cast, preferDirect);
                    try {
                        encode(ctx, cast, buf);
                    } finally {
                        ReferenceCountUtil.release(cast);
                    }

                    if (buf.isReadable()) {
                        ctx.write(buf, promise);
                    } else {
                        buf.release();
                        ctx.write(Unpooled.EMPTY_BUFFER, promise);
                    }
                    buf = null;
                } else {
                    ctx.write(msg, promise);
                }
                */
//        ByteBuf byteBuf = Unpooled.copiedBuffer("abcdabcdefghabcd", CharsetUtil.UTF_8);
//        ctx.writeAndFlush(byteBuf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("MyClientHandler.channelRead0() 服务器的ip=" + ctx.channel().remoteAddress() + " 收到msg=" + msg);
    }
}
