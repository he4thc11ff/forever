package scau.lizl.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class NettyServer {
    public static void main(String[] args) {
        // 创建BossGroup和WorkGroup
        // 说明
        // 1.创建两个线程组 bossGroup workerGroup
        // 2.bossGroup 只处理连接请求；客户端的业务处理交给workerGroup
        // 3.两个都是无限循环
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 使用链式编程来进行设置
            bootstrap.group(bossGroup, workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) // 使用NioSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) //设置线程队列等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 创建通道初始化对象(匿名对象)
                        // 给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 要先加入proto解码器
                            ch.pipeline().addLast(new ProtobufDecoder(StudentProto.Student.getDefaultInstance()));
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    }); // 给workerGroup的EventLoop的对应的管道设置处理器

            System.out.println("服务器 is ready");

            // 启动服务器
            // 绑定一个端口并且同步，生成了一个ChannelFuture对象
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();

            // 异步好处：
            // 相比传统阻塞IO，执行IO操作后线程会被阻塞，直到操作完成
            // 异步处理不会造成线程阻塞，线程在IO操作期间可以执行别的程序
            // 在高并发下会更稳定和得到更高的吞吐量
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("监听 6668端口 成功");
                    } else {
                        System.out.println("监听 6668端口 失败");
                    }
                }
            });

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
