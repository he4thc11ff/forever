package scau.lizl.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/*
说明
1. 我们自定义一个Handler需要继承netty规定好的某个handlerAdapter
2. 这时，我们自定义的一个Handler才能称为一个handler (遵守netty的规范)
3.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 读取数据事件（这里我们可以读取客户端发送的信息）
    /*
    1. ChannelHandlerContext ctx：上下文对象，含有管道pipeline(很多handler流式处理)、通道socketChannel
    2. Object msg：客户端发送的数据 默认Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("server ctx = " + ctx);
//
//        // 将 msg 转成一个 ByteBuf(netty提供的性能更高，不是原生的nio的byteBuffer)
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println("客户端发送消息是：" + byteBuf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址：" + ctx.channel().remoteAddress());

//        // 假如我们这里有一个非常耗时的业务 -> 异步执行，提交到该channel对应的
//        // NioEventLoop的taskQueue中，
//        Thread.sleep(10 * 1000); // 服务器阻塞在这里了
//        ctx.writeAndFlush(Unpooled.copiedBuffer("10s long time，客户端~", CharsetUtil.UTF_8));
//        System.out.println("go on ...");

        // 解决方案1 用户自定义普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("10s long time，客户端~", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 解决方案2 定时任务
//        ctx.channel().eventLoop().schedule(new Runnable())

        System.out.println("go on ...");
    }

    // 读取数据完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将消息写入到缓冲区并发送给通道
        // 要先给发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端~", CharsetUtil.UTF_8));

    }

    // 处理异常，一般需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close(); // 或者ctx.close()
    }
}
