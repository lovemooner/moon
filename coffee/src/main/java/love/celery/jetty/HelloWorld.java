//package love.celery.jetty;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.ServletException;
//
//import java.io.IOException;
//
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.Request;
//import org.eclipse.jetty.server.handler.AbstractHandler;
//
///**
// * Author: lovemooner
// * Date: 2017/5/9 17:07
// */
//public class HelloWorld extends AbstractHandler {
//
//
//    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//        request.setHandled(true);
//        response.getWriter().println("<h1>HelloWorld1</h1>");
//    }
//
//    public static void main(String[] args) throws Exception {
//        Server server = new Server(8080);
//        server.setHandler(new HelloWorld());
//        server.start();
//        server.join();
//
//    }
//
//}