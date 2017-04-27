package nio.reactor.multi;

import nio.reactor.multi.reactor.ServerReactor;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		new Thread(new ServerReactor(9003)).start();
	}
	
}
