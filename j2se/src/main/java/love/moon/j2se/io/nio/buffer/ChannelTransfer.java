package love.moon.j2se.io.nio.buffer;

import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author dongnan
 * @date 2020/4/23 16:33
 */
public class ChannelTransfer {
    public static void main(String[] argv) throws Exception {
        String files[] = new String[1];
        files[0] = "D://db.txt";
        catFiles(Channels.newChannel(System.out), files);
    }

    private static void catFiles(WritableByteChannel target, String[] files)
            throws Exception {
        for (int i = 0; i < files.length; i++) {
            FileInputStream fis = new FileInputStream(files[i]);
            FileChannel channel = fis.getChannel();
            channel.transferTo(0, channel.size(), target);
            channel.close();
            fis.close();
        }
    }
}
