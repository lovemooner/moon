package love.moon.dubbo.demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by lovemooner on 2017/5/2.
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {
    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceImpl.class);
    public String sayHello(String name) {
        System.out.println("Server:receiver name--"+name);
        return "Hello " + name;
    }
}
