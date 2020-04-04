package scau.lizl.netty.in_out_bound_handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

// OutBound
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 加入一个出站的handler，对数据进行编码
        pipeline.addLast(new MyLongToByteEncoder());

        // 这是一个入站的解码器(入站handler)
        pipeline.addLast(new MyByteToLongDecoder2());

        // 加入自定义的handler处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
