package love.moon.design.factory.factoryMethod;

import love.moon.design.factory.IProduct;

public class Main {

    public static void main(String[] args) {
        IFactory factory = new FactoryA();
        IProduct product = factory.create();
        product.productMethod();

        factory = new FactoryB();
        product = factory.create();
        product.productMethod();

    }
}
