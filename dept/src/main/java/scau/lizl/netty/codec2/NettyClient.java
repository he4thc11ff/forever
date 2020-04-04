package scau.lizl.netty.codec2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class NettyClient {
    public static void main(String[] args) throws Exception{
        // 客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();

        // 创建客户端的启动对象，是 Bootstrap 而不是ServerBootstrap
        Bootstrap bootstrap = new Bootstrap();

        try {
            // 设置参数
            bootstrap.group(group) // 设置线程组
                    .channel(NioSocketChannel.class) // 设置客户端通道的实现类，netty会用反射来处理
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 要先加入proto编码器
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast(new NettyClientHandler()); // 加入自己的处理器
                        }
                    });

            System.out.println("客户端 is ready");

            // 启动客户端去连接服务器端
            // 关于 channelFuture， 设计到netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("localhost", 6668).sync();

            // 监听关闭通道的事件
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
