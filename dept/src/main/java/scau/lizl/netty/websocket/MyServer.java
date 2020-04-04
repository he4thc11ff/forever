package scau.lizl.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)) // 在BoosGroup增加一个日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 因为基于http协议，使用http的编码核解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 是以块方式写，添加chunkedWtite处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /*
                            说明
                            1. http数据在传输过程中是分段的，HttpObjectAggregator，就是可以将多个段聚合起来
                            2. 这就是为什么当浏览器发送大量数据时会发出多次http请求
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                            说明
                            1. 对于websocket。它的数据是以 帧(frame) 的形式传递
                            2. 可以看到webSocketFrame 下面有6个子类 如TextWebSocketFrame ctrl + n
                            3. 浏览器请求时，ws://localhost:7000/hello 表示请求的URI
                            4. WebSocketServerProtocolHandler 将 http 协议升级为 ws 协议 从而保持长连接
                            5. 是通过一个状态码 101 -> Status Code:101 Switching Protocols
                            6. 可以做浏览器的聊天系统QAQ
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            // 自定义的handler，处理浏览器请求、业务逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
