package love.moon.design.factory.factoryMethod;

import love.moon.design.factory.BusinessB;
import love.moon.design.factory.IBusiness;

public class FactoryB implements IFactory {

    public IBusiness create() {
        return new BusinessB();
    }

    public static void main(String[] args) {

    }
}