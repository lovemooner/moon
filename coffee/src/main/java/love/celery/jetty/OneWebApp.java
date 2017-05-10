package love.celery.jetty;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Author: lovemooner
 * Date: 2017/5/9 17:15
 */
public class OneWebApp {

    public static void main(String[] args) throws Exception {
        String jetty_home = "jetty";
        int port = 8020;

        Server server = new Server();

        Connector connector = new SelectChannelConnector();
        connector.setPort(port);
        server.addConnector(connector);

        WebAppContext webapp = new WebAppContext(jetty_home + "/web", "/jetty");
        webapp.setDefaultsDescriptor(jetty_home + "/etc/webdefault.xml");

        server.setHandler(webapp);

        server.start();
        //server.join();
    }
}
