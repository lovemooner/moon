package love.moon.design.factory.factoryMethod;

import love.moon.design.factory.BusinessB;
import love.moon.design.factory.IBusiness;

public class FactoryA implements IFactory {

    public IBusiness create() {
        return new BusinessB();
    }
}
