import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private static final Logger LOG = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        LOG.info("test");
        LOG.info("test1 {} ",3);
    }
}

