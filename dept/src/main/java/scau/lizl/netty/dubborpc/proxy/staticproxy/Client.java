package scau.lizl.netty.dubborpc.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        // 优点：可以在代理对象里面对方法增强
        // 缺点：代理类也要实现接口，接口代码改变代理类的代码也要改变
        TeachDAOProxy teachDAOProxy = new TeachDAOProxy(new TeachDAO());
        teachDAOProxy.teach();
    }
}
