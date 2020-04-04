package scau.lizl.netty.dubborpc.demo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context; // 上下文
    private String result; // 调用后返回的结果
    private String para; // 客户端调用方法时传入的参数

    //1 与服务器的连接创建成功后，就会被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        // 因为我们在其它方法会使用到 ctx
        context = ctx;
    }

    //4 收到服务器的数据后，就会被调用
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead " + Thread.currentThread().getName());
        result = (String) msg;

        // 唤醒等待的线程 channelRead 和 call 有同步关系(synchronized 加类锁)
        notify();
    }

    //3 被代理对象调用，发送数据给服务器 -> wait -> 等待被channelRead唤醒 -> 返回结果
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call1 " + Thread.currentThread().getName());
        context.writeAndFlush(para);

        // 进行wait
        wait(); // 等待 channelRead 获取到服务器的结果后

        System.out.println("call2");
        //5 finished
        return result;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    //2
    void setPara(String para) {
        System.out.println("setPara");
        this.para = para;
    }
}
