package scau.lizl.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/*
说明
1. SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter 子类
2. HttpObject 客户端和服务器端相互通讯的数据被封装成 HttpObject 类型
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    // 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断msg 是不是 httpRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("pipeline: " + ctx.pipeline().hashCode() + " TestHttpServerHandler: " + this.hashCode());

            System.out.println("msg 类型：" + msg.getClass());
            System.out.println("客户端地址：" + ctx.channel().remoteAddress());

            // 回复信息给浏览器 [http协议]
            ByteBuf content = Unpooled.copiedBuffer("hello，俺是服务器", CharsetUtil.UTF_8);

            // 构造一个http的响应，即httpResponse
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将构建好的 httpResponse 返回
            ctx.writeAndFlush(httpResponse);

        }
    }
}
