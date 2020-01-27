package love.moon.j2se.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousFileChannel100 {

    public static void main(String[] args) {

        callBack();
    }


    public static void callBack(){
        try {
            Path file = Paths.get("./pom.xml");
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate(300 * 1024 * 1024);
            final long start = System.nanoTime();
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("Bytes read [" + result + "]");
                    long end = System.nanoTime();
                    System.out.println("Total cost time : " + (end - start));
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println(exc.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void future(){
        try {

            Path file = Paths.get("./pom.xml");

            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer = ByteBuffer.allocate(100_000);
            Future<Integer> result = channel.read(buffer, 0);
            while (!result.isDone()) {
                // do something
            }
            Integer bytesRead = result.get();
            System.out.println("Bytes read [" + bytesRead + "]");
        } catch (IOException | ExecutionException |
                InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
