package bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * User: lovemooner
 * Date: 17-4-11
 * Time: 下午5:55
 */
public class IOClient {
    private Socket socket;

    public IOClient() throws IOException {
        socket = new Socket("localhost", 2345);
    }

    public void start() {
        try {
            OutputStream out = socket.getOutputStream();
            while (true) {
                out.write("Hello".getBytes());
                Thread.sleep(1000L);

            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public static void main(String[] args) throws IOException {
        IOClient client = new IOClient();
        client.start();
    }

}
