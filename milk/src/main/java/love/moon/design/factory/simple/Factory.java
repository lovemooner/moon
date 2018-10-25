package love.moon.design.factory.simple;

import love.moon.design.factory.IProduct;
import love.moon.design.factory.ProductA;
import love.moon.design.factory.ProductB;

public class Factory {

    public static IProduct create(String type) {
        IProduct product = null;
        if ("A".equals(type)) {
            product = new ProductA();
        } else if ("B".equals(type)) {
            product = new ProductB();
        }
        return product;

    }
}
