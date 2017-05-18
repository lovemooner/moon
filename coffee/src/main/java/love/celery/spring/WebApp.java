package love.celery.spring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;



/**
 * Author: lovemooner
 * Date: 2017/5/18 15:57
 */
public class WebApp {
    public static final String BASE_PATH=System.getProperty("user.dir")+"/src/main/webapp";
    public static final String WEB_XML_PATH=BASE_PATH+"/WEB-INF/web.xml";


    public static void main(String[] args) {
        try {
            Server server = new Server(8080);

            WebAppContext context = new WebAppContext();
            context.setContextPath("/servlet");
            context.setDescriptor(WEB_XML_PATH); // 指定web.xml配置文件
            context.setResourceBase(BASE_PATH);// 指定webapp目录
            context.setParentLoaderPriority(true);

            server.setHandler(context);
            server.start();
            server.join();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}