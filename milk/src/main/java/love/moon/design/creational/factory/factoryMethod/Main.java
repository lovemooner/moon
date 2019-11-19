package love.moon.design.creational.factory.factoryMethod;

import love.moon.design.creational.factory.IBusiness;

public class Main {

    public static void main(String[] args) {
        IBusiness productA = new FactoryB().create();
        productA.doService();
        IBusiness productB = new FactoryB().create();
        productB.doService();

    }
}
