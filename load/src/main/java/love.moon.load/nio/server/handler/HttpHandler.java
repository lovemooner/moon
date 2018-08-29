package love.moon.load.nio.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import love.moon.load.jload.bean.ByteBufToBytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Author: lovemooner
 * Date: 2018/8/27 17:38
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    private ByteBufToBytes reader;

    private static Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            if (HttpMethod.GET.equals(request.getMethod())) {
                System.out.println("messageType:" + request.headers().get("messageType"));
                String param1 = request.headers().get("Param1");
                System.out.println("businessType:" + request.headers().get("businessType"));
            } else if (HttpMethod.POST.equals(request.getMethod())) {
                if (HttpHeaders.isContentLengthSet(request)) {
                    reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(request));
                }
            }

        }

        if (msg instanceof HttpContent) {
            if (reader != null) {
                HttpContent httpContent = (HttpContent) msg;
                ByteBuf content = httpContent.content();
                reader.reading(content);
                content.release();
                if (reader.isEnd()) {
                    String resultStr = new String(reader.readFull());
                    System.out.println("Client said:" + resultStr);
                }
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer("I am ok"
                    .getBytes()));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            ctx.write(response);
            ctx.flush();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("HttpServerInboundHandler.channelReadComplete");
        ctx.flush();
    }
}
