package love.moon.design.factory.factoryMethod;

import love.moon.design.factory.IProduct;
import love.moon.design.factory.ProductB;

public class FactoryB implements IFactory {

    public IProduct create() {
        return new ProductB();
    }
}