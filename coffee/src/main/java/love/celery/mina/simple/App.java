package love.celery.mina.simple;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/10 17:30
 */
public class App {

    public static void main(String[] args) throws IOException {
        Reactor reactor=new Reactor(8020);
        reactor.start();
    }
}
