package scau.lizl.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/*
说明
1. 我们自定义一个Handler需要继承netty规定好的某个handlerAdapter
2. 这时，我们自定义的一个Handler才能称为一个handler (遵守netty的规范)
3.
 */
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentProto.Student> {

//    // 读取数据事件（这里我们可以读取客户端发送的信息）
//    /*
//    1. ChannelHandlerContext ctx：上下文对象，含有管道pipeline(很多handler流式处理)、通道socketChannel
//    2. Object msg：客户端发送的数据 默认Object
//     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 读取客户端发送的Student，如果继承SimpleInbound，则无需在这里强转
//        StudentProto.Student student = (StudentProto.Student) msg;
    //        System.out.println("客户端发来的student: id - " + student.getId() + " name - " + student.getName());
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentProto.Student student) throws Exception {
                System.out.println("客户端发来的student: id - " + student.getId() + " name - " + student.getName());
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
