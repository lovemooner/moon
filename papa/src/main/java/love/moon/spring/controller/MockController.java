package love.moon.spring.controller;

import love.moon.mock.BioServer;
import love.moon.mock.ThreadRunnable;
import love.moon.util.JmxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2017/6/23 15:57
 */
@RestController
public class MockController {
    private static Logger LOG = LoggerFactory.getLogger(MockController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/thread", method = RequestMethod.GET)
    public String thread(HttpServletRequest request) {
        for (int i = 0; i < 100; i++) {
            ThreadRunnable tt = new ThreadRunnable();
            Thread t = new Thread(tt);
            t.start();
        }
        return "start thread";
    }

    @RequestMapping(value = "/nio", method = RequestMethod.GET)
    public String nioService(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/io", method = RequestMethod.GET)
    public String ioService(HttpServletRequest request) throws IOException {
        BioServer server = null;
        server = new BioServer();
        server.listening();
        return "start ioService";
    }

    @RequestMapping(value = "/maxThreadNum", method = RequestMethod.GET)
    public void calMaxThreadNum() {
        JmxUtil.getMemoryMXBean();
        for (int i = 0; ; i++) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
        }
    }


}

class HoldThread extends Thread {
    CountDownLatch cdl = new CountDownLatch(1);

    public HoldThread() {
        this.setDaemon(true);
    }

    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }
    }
}