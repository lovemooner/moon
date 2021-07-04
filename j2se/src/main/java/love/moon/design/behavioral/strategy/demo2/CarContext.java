package love.moon.design.behavioral.strategy.demo2;

/**
 *  销售部门----服务端
 * @author : ndong
 * date : 2021/6/27 18:23
 * desc :
 */
public class CarContext {
    AudiCar audiCar = null;

    public CarContext(AudiCar audiCar) {
        this.audiCar = audiCar;
    }

    public void orderCar(){
        this.audiCar.makeCar();
    }
}
