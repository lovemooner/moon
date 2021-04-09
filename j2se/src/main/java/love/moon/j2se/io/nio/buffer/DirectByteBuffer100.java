package love.moon.j2se.io.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author : ndong
 * date : 2021/4/9 15:01
 * desc :
 */
public class DirectByteBuffer100 {

    /**
     * 手动开启
     */
    @Test
    public void test1() {
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(100);
        System.out.println("ss");
    }

    @Test
    public void test2() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.putChar('a');
        System.out.println(buffer);
        buffer.putInt(10);
        System.out.println(buffer);
        //限制大小
        buffer.limit(512);
        //空间容量
        System.out.println(buffer.capacity());
        System.out.println(buffer.remaining());

        System.out.println(buffer.clear());
        System.out.println(buffer.remaining());
    }

}
