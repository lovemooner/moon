package love.moon.design.structural.proxy;

/**
 * @auther dongnan
 * @date 2019/11/18 15:04
 * @describe
 */
public class Service implements IService {
    @Override
    public void doService() {
        System.out.println("doService");
    }
}
