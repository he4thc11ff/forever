package scau.lizl.netty.dubborpc.demo.provider;

import scau.lizl.netty.dubborpc.demo.netty.NettyServer;

public class ServerBootStrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
