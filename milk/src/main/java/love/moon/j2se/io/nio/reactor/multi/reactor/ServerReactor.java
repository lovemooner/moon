package love.moon.j2se.io.nio.reactor.multi.reactor;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;


public class ServerReactor implements Runnable {
    private static Logger LOGGER = Logger.getLogger(ServerReactor.class);
    private ServerSocketChannel serverSocketChannel;

    public ServerReactor(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", port), 1024);
        serverSocketChannel.configureBlocking(false);
        LOGGER.info(String.format("Server : Server Start.----%d", port));
    }

    public void run() {
        try {
            new ServerDispatcher(serverSocketChannel, SelectorProvider.provider()).execute();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
