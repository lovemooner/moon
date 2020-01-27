package love.moon.j2se.io.nio.reactor.multi;


import love.moon.j2se.io.nio.reactor.multi.reactor.ServerReactor;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		new Thread(new ServerReactor(9003)).start();
	}
	
}
