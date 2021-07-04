package love.moon.design.behavioral.strategy.demo2;

/**
 * @author : ndong
 * date : 2021/6/27 18:22
 * desc :
 */
abstract class AudiCar{
    private String name;

    public abstract void makeCar();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
