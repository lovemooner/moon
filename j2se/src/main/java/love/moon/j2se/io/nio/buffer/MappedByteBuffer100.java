package love.moon.j2se.io.nio.buffer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author dongnan
 * @date 2020/4/23 16:14
 */
public class MappedByteBuffer100 {

    public static void main(String[] args) throws Exception {
        File file = new File("E://my.cnf");
        long len = file.length();
        byte[] ds = new byte[(int) len];
        FileChannel fileChannel=new FileInputStream(file).getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, len);
        for (int offset = 0; offset < len; offset++) {
            byte b = mappedByteBuffer.get();
            ds[offset] = b;
        }
        Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
        while (scan.hasNext()) {
            System.out.print(scan.next() + " ");
        }
    }
}
