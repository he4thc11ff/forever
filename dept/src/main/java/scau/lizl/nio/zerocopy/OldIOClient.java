package scau.lizl.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

public class OldIOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7001);

        String filename = "protoc-3.6.1-win32.zip";
        FileInputStream fileInputStream = new FileInputStream(filename);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] byteBuffer = new byte[4096];
        long total = 0;

        long startTime = System.currentTimeMillis();
        while (true) {
            int read = fileInputStream.read(byteBuffer, 0, byteBuffer.length);

            if (read == -1) {
                break;
            } else {
                dataOutputStream.write(byteBuffer);
                total += read;
            }
        }

        System.out.println("发送总字节数：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        fileInputStream.close();
    }
}
