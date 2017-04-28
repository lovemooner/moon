package love.moon.web.context;

import love.moon.jdbc.DataSourceMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lovemooner on 2017/4/27.
 */
public class DBConfigListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(DBConfigListener.class);
    private ServletContext servletContext = null;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        String path = servletContext.getRealPath("/");
        LOG.info("property file path====", path);
        Properties prop = new Properties();
        try {
            InputStream inputStream =new FileInputStream(path+"db.properties");
            prop.load(inputStream);
            //设置常量
            DataSourceMgr.DRIVER_CLASS_NAME = prop.getProperty("driverClassName");
            DataSourceMgr.URL = prop.getProperty("url");
            DataSourceMgr.USERNAME = prop.getProperty("username");
            DataSourceMgr.PASSWORD = prop.getProperty("password");
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
