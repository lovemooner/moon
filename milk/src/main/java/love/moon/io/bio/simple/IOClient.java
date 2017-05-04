package love.moon.io.bio.simple;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Author: lovemooner
 * Date: 2017/5/3 15:12
 */
public class IOClient extends Thread{
    private static Logger LOG = Logger.getLogger(IOClient.class);

    public void run() {

    }

    public static void main(String[]args){
        IOClient t1=new IOClient();
        t1.start();
    }

}
