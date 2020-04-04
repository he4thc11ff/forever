package scau.lizl.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动。。。");

        while (true) {
            System.out.println("线程信息 id=" + Thread.currentThread().getId() + " name=" + Thread.currentThread().getName()); //主线程
            System.out.println("等待连接。。");
            final Socket socket = serverSocket.accept(); // 阻塞
            System.out.println("连接到一个客户端");

            // 每连接一个客户端，就创建一个线程与之通信
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            System.out.println("线程信息 id=" + Thread.currentThread().getId() + " name=" + Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];

            // 循环读取客户端发来的信息
            while (true) {
                System.out.println("线程信息 id=" + Thread.currentThread().getId() + " name=" + Thread.currentThread().getName());
                int read = inputStream.read(bytes); // 阻塞
                System.out.println("等待信息。。");
                if (read != -1) {
                    String message = new String(bytes, 0, read);
                    System.out.println("收到信息 -> " + message);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接。。。");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
