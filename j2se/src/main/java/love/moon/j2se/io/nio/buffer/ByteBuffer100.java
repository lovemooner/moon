package love.moon.j2se.io.nio.buffer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBuffer100 {


    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile(new File(
                "resources/ByteBufferTest.txt"), "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            print1(buffer);
            print2(buffer);
            buffer.clear();
            buffer.mark();
        }
        channel.close();
        file.close();
    }

    /**
     * 打印ByteBuffer中所有的字节
     */
    private static void print1(ByteBuffer buffer) {
        System.out.print("Print by print1 : ");
        for (byte b : buffer.array()) {
            System.out.print((char) b);
        }
        System.out.println();
    }

    /**
     * 按照字节逐个打印
     */
    private static void print2(ByteBuffer buffer) {
        System.out.print("Print by print2 : ");
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        System.out.println();
    }
}