package scau.lizl.netty.dubborpc.demo.provider;

import scau.lizl.netty.dubborpc.demo.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {

    // client每次调用都会产生一个新的HelloServiceImpl，此处改为static 就会累积了
    private static int count = 0;

    @Override
    public String hello(String mes) {
        System.out.println("收到客户端的消息：" + mes);
        // 根据 mes 返回不同的结果
        if (mes != null) {
            return "你好客户端，我已经收到你的消息[" + mes + "] 第 " + (++count) + "次";
        } else {
            return "你好客户端，你发的是个JB？";
        }
    }
}
