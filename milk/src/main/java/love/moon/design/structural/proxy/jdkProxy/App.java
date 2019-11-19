package love.moon.design.structural.proxy.jdkProxy;

import love.moon.design.structural.proxy.IService;
import love.moon.design.structural.proxy.Service;

public class App {

    public static void main(String[] args) {
        JdkProxy proxy = new JdkProxy();
        IService service = (IService) proxy.bind(new Service());
        service.doService();
    }

}
