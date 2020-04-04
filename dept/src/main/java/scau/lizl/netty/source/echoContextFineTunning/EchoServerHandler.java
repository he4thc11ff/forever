/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package scau.lizl.netty.source.echoContextFineTunning;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.concurrent.Callable;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // 静态 被所有EchoServerHandler共享
    // group 充当业务线程池, 可以将任务提交到该线程池中
    static final EventExecutorGroup group = new DefaultEventExecutorGroup(16);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 输出线程名称
        System.out.println("EchoServerHandler 的线程是 " + Thread.currentThread().getName()); // nioEventLoopGroup-3-1

        // 普通方式
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String content = new String(bytes, Charset.forName("utf-8"));
        // 休眠10s
        Thread.sleep(10 * 1000);
        System.out.println("普通方式调用的 线程是 " + Thread.currentThread().getName());
        ctx.writeAndFlush(Unpooled.copiedBuffer("task2 10s long time", CharsetUtil.UTF_8));

        // 解决方案1 将任务提交到本IO线程池 -> 执行任务时还是属于同一个线程，所以会阻塞
//        ctx.channel().eventLoop().execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5 * 1000); // 同一个线程在做事情，阻塞在这里啦
//                    // 输出线程名称
//                    System.out.println("EchoServerHandler execute 的线程是 " + Thread.currentThread().getName()); // nioEventLoopGroup-3-1
//                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端~ 5s long time", CharsetUtil.UTF_8));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        ctx.channel().eventLoop().execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5 * 1000); // 同一个线程在做事情，阻塞在这里啦
//                    // 输出线程名称
//                    System.out.println("EchoServerHandler execute 的线程是 " + Thread.currentThread().getName()); // nioEventLoopGroup-3-1
//                    ctx.writeAndFlush(Unpooled.copiedBuffer("task2 5s long time", CharsetUtil.UTF_8));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        // 解决方案2 将任务提交到自定义业务线程池group，不是本IO线程池，就不会阻塞了
//        group.submit(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                // 接收客户端信息
//                ByteBuf buf = (ByteBuf) msg;
//                byte[] bytes = new byte[buf.readableBytes()];
//                buf.readBytes(bytes);
//                String content = new String(bytes, Charset.forName("utf-8"));
//                // 休眠10s
//                Thread.sleep(10 * 1000);
//                System.out.println("group.submit的 call 线程是 " + Thread.currentThread().getName());
//                //底层write方法 if(executor.inEventLoop()) else
//                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端~ 10s long time", CharsetUtil.UTF_8));
//                return null;
//            }
//        });
//        group.submit(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                // 接收客户端信息
//                ByteBuf buf = (ByteBuf) msg;
//                byte[] bytes = new byte[buf.readableBytes()];
//                buf.readBytes(bytes);
//                String content = new String(bytes, Charset.forName("utf-8"));
//                // 休眠10s
//                Thread.sleep(10 * 1000);
//                System.out.println("group.submit的 call 线程是 " + Thread.currentThread().getName());
//                ctx.writeAndFlush(Unpooled.copiedBuffer("task2 10s long time", CharsetUtil.UTF_8));
//                return null;
//            }
//        });

        System.out.println("go on");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
