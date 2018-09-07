package love.moon.listener;

/**
 * Author: lovemooner
 * Date: 2017/6/15 13:25
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


public class ListenerDemo implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ListenerDemo.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {


    }



    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

