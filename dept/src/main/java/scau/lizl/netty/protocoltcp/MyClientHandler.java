package scau.lizl.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("天气冷，吃火锅");
        list.add("今天天气冷，吃火锅");
        list.add("今天天气冷，吃火锅吗");
        list.add("今天天气冷");
        list.add("吃");

        // 客户端发送10条数据 "今天天气冷，吃火锅"
        for (int i = 0; i < 5; i++) {
//            String msg = "今天天气冷，吃火锅";
            String msg = list.get(i);
            byte[] content = msg.getBytes(Charset.forName("utf-8"));
            int length = content.length;

            // 创建协议包对象
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);

            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到消息如下：");
        System.out.println("长度 = " + len);
        System.out.println("内容 = " + new String(content, Charset.forName("utf-8")));
        System.out.println("客户端接收消息数量 " + (++count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
