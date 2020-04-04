package scau.lizl.netty.dubborpc.proxy.dynamicproxy;

public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new TeachDAO());
        ITeachDAO proxy = (ITeachDAO) proxyFactory.getProxyInstance();
        proxy.teach();
        proxy.miao();
    }
}
