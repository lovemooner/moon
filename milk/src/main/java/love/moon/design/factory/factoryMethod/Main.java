package love.moon.design.factory.factoryMethod;

import love.moon.design.factory.IBusiness;

public class Main {

    public static void main(String[] args) {
        IFactory factory = new FactoryA();
        IBusiness product = factory.create();
        product.doService();

        factory = new FactoryB();
        product = factory.create();
        product.doService();

    }
}
