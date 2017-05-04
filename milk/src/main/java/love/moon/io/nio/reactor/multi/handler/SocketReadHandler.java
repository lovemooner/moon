package love.moon.io.nio.reactor.multi.handler;

import love.moon.io.nio.reactor.multi.reactor.ServerDispatcher;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SocketReadHandler extends SocketHandler{
     private static Logger LOGGER = Logger.getLogger(SocketReadHandler.class);
	private  int BLOCK = 4096;
	private  ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);
	
	public SocketReadHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException{
		super(dispatcher, sc, selector);
	}
	
	@Override
	public void runnerExecute(int readyKeyOps) throws IOException {
		// TODO Auto-generated method stub
		int count = 0;
		if (SelectionKey.OP_READ == readyKeyOps)
		{
            receiveBuffer.clear();
            count = socketChannel.read(receiveBuffer);
            if (count > 0) {  
            	LOGGER.info("Server : Readable.");
            	receiveBuffer.flip();
	            byte[] bytes = new byte[receiveBuffer.remaining()];
	            receiveBuffer.get(bytes);
	            String body = new String(bytes, "UTF-8"); 
	            LOGGER.info("Server : Receive :" + body);
	            socketChannel.register(dispatcher.getWriteSelector(), SelectionKey.OP_WRITE);
            }  
		}
	}
}
