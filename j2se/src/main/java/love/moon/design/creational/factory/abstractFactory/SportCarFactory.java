package love.moon.design.creational.factory.abstractFactory;

public class SportCarFactory  implements CarFactory {

    public BenzCar getBenzCar() {
        return new BenzSportCar();
    }

    public TeslaCar getTeslaCar() {
        return new TeslaSportCar();
    }
}
