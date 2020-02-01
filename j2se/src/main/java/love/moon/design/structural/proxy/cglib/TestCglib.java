package love.moon.design.structural.proxy.cglib;

public class TestCglib {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        CglibProxyService service = (CglibProxyService) proxy.getInstance(new CglibProxyService());
        service.doService();
    }
}
