package love.moon.design.creational.factory.factoryMethod;

import love.moon.design.creational.factory.BusinessA;
import love.moon.design.creational.factory.IBusiness;

public class FactoryA implements IFactory {

    public IBusiness create() {
        return new BusinessA();
    }
}
