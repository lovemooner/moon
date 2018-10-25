package love.moon.design.factory.factoryMethod;

import love.moon.design.factory.IProduct;
import love.moon.design.factory.ProductA;

public class FactoryA implements IFactory {

    public IProduct create() {
        return new ProductA();
    }
}
