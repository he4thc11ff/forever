package scau.lizl.test;

import net.jpountz.lz4.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class testLz4 {
    public static void main(String[] args) throws Exception {
        byte[] data = "12345345234572".getBytes("UTF-8");
        final int decompressedLength = data.length;

//        LZ4FrameOutputStream outStream = new LZ4FrameOutputStream(new FileOutputStream(new File("test.lz4")));
//        outStream.write(data);
//        outStream.close();

        byte[] restored = new byte[decompressedLength];
        LZ4FrameInputStream inStream = new LZ4FrameInputStream(new FileInputStream(new File("test.lz4")));
        inStream.read(restored);

        String result = new String(restored, "UTF-8");
        System.out.println(result);

        inStream.close();
    }
}
