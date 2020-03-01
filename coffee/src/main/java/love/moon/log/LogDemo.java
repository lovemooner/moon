package love.moon.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: lovemooner
 * Date: 17-4-18
 * Time: 下午2:52
 */
public class LogDemo {
    private static final Logger LOG = LoggerFactory.getLogger(LogDemo.class);

    public static void main(String[] args) {
        LOG.debug("info-------------");
    }
}
