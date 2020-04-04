package scau.lizl.netty.codec2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import scau.lizl.netty.codec.StudentProto;

/*
说明
1. 我们自定义一个Handler需要继承netty规定好的某个handlerAdapter
2. 这时，我们自定义的一个Handler才能称为一个handler (遵守netty的规范)
3.
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        //根据dataType 显示不同的信息
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == MyDataInfo.MyMessage.DataType.Student) {
            System.out.println("是个学生");
            System.out.println(msg.getStudent());
        } else if(dataType == MyDataInfo.MyMessage.DataType.Worker) {
            System.out.println("是个工人");
            System.out.println(msg.getWorker());
        } else {
            System.out.println("不能识别该数据类型");
        }
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
