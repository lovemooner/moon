package love.moon.io.aio;//package aio;
//
///**
// * User: lovemooner
// * Date: 17-4-7
// * Time: 下午3:54
// */
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//
//public class AIOClient {
//
//    public static void main(String... args) throws Exception {
//        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
//        client.connect(new InetSocketAddress("localhost", 9888));
//        client.write(ByteBuffer.wrap("test".getBytes())).get();
//    }
//}