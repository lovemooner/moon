package love.moon.j2se.net;

import lombok.Data;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author : ndong
 * date : 2021/6/5 23:08
 * desc :
 */
@Data
public class Request {

    private SocketChannel socketChannel;
    private String requestId;
    private boolean readed;

}
