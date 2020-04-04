package scau.lizl.netty.in_out_bound_handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 入站的handler，进行解码 MyByteToLongDecoder
        pipeline.addLast(new MyByteToLongDecoder2());

        // 出站的handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());

        // 加入自定义handler 处理业务逻辑
        pipeline.addLast(new MyServerHandler());
    }
}
