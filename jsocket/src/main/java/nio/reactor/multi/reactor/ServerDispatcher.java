package nio.reactor.multi.reactor;

import nio.reactor.multi.handler.SocketAcceptHandler;
import nio.reactor.multi.handler.SocketHandler;
import nio.reactor.multi.handler.SocketReadHandler;
import nio.reactor.multi.handler.SocketWriteHandler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class ServerDispatcher {
	
	private ServerSocketChannel serverSocketChannel;
	private Selector[] selectors = new Selector[3];

	public ServerDispatcher(ServerSocketChannel serverSocketChannel, SelectorProvider selectorProvider) throws IOException {
		this.serverSocketChannel = serverSocketChannel;
		for (int i = 0; i < 3; i++) {
			selectors[i] = selectorProvider.openSelector();
		}
	}

	public Selector getAcceptSelector() {
		return selectors[0];
	}

	public Selector getReadSelector() {
		return selectors[1];
	}

	public Selector getWriteSelector() {
		return selectors[2];
	}

	public void execute() throws IOException {
		SocketHandler r = new SocketAcceptHandler(this, serverSocketChannel, getAcceptSelector());
		new Thread(r).start();

		r = new SocketReadHandler(this, serverSocketChannel, getReadSelector());
		new Thread(r).start();

		r = new SocketWriteHandler(this, serverSocketChannel, getWriteSelector());
		new Thread(r).start();
	}

}
