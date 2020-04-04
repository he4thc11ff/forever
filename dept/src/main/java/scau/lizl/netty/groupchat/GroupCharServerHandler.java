package scau.lizl.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupCharServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义channel组，管理所有的channel | 也可以自己定义一个ArrayList<Channel>
    // GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
    // 一定要加static 因为这个channelGroup是所有GroupCharServerHandler 共享的
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // handlerAdded 表示一旦连接建立，第一个被执行
    // 将当前channel 加入到channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        // 将该客户加入聊天的信息推送给其它在线的客户端
        // writeAndFlush 底层就是遍历的
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天" + sdf.format(new Date()) + "\n");
    }

    // channelActive 表示channel处于活动状态，提示xx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了~");
    }

    // channelInactive 表示channel处于非活跃状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线");
    }

    // handlerRemoved 表示断开连接，将xx离开信息推送给当前在线的客户
    // 心跳检测的意义：手机飞行模式（即不能上网），或强制关机（停电）等
    // 都会导致服务器端不能感知客户端的连接状态
    // 心跳包的检测才能真正做到感知客户端的连接状态
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // handlerRemoved调用时，channelGroup已经移除了当前的channel
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "断开连接");
        System.out.println("channelGroup's size = " + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        // 根据不同的情况回复不同的消息
        channelGroup.forEach(ch -> {
            if (channel != ch) { // 不是当前channel，则转发消息
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + "发送了消息 -> " + msg +"\n");
            } else { // 回显
                ch.writeAndFlush("[自己]发送了消息 -> " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭通道
        ctx.close();
    }
}
