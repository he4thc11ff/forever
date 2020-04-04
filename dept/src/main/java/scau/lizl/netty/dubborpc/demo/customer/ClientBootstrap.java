package scau.lizl.netty.dubborpc.demo.customer;

import scau.lizl.netty.dubborpc.demo.netty.NettyClient;
import scau.lizl.netty.dubborpc.demo.publicinterface.HelloService;

public class ClientBootstrap {

    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws Exception {
        // 创建一个消费者
        NettyClient customer = new NettyClient();

        // 创建代理对象
        HelloService helloService = (HelloService) customer.getBean(HelloService.class, providerName);

        // 通过代理对象调用服务提供者的方法(服务)
        for (;;) {
            Thread.sleep(3 * 1000);
            String res = helloService.hello("你好 dubbo");
            System.out.println("调用的结果 res = " + res);
        }
    }
}
