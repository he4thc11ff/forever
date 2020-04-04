package scau.lizl.nio.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7001);

        while (true) {
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            byte[] byteArray = new byte[4096];
            long total = 0;

            long read = 0;
            while (read != -1) {
                read = dataInputStream.read(byteArray, 0, byteArray.length);
                total += read;
            }

            System.out.println("共接收：" + total);
        }

    }
}
