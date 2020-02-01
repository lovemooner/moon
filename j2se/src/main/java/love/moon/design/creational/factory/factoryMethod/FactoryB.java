package love.moon.design.creational.factory.factoryMethod;

import love.moon.design.creational.factory.BusinessB;
import love.moon.design.creational.factory.IBusiness;

public class FactoryB implements IFactory {

    public IBusiness create() {
        return new BusinessB();
    }

    public static void main(String[] args) {

    }
}