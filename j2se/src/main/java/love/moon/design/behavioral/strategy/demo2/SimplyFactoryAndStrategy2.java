package love.moon.design.behavioral.strategy.demo2;

/**
 * 客户----客户端（这个客户是内行，什么都懂，他说我要A6，销售部门立刻给他a6，所以销售部门不用很懂）
 *
 * @author : ndong
 * date : 2021/6/27 18:24
 * desc :
 */
public class SimplyFactoryAndStrategy2 {


    public static void main(String[] args) {

        AudiCar car = new AudiA6();
        car.setName("a6");
        CarContext context = new CarContext(car);
        context.orderCar();
    }
}
