package love.moon.design.factory.simple;

import love.moon.design.factory.IProduct;

public class Main {

    public static void main(String[] args) {
        IProduct productA = Factory.create("A");
        productA.productMethod();

        IProduct productB = Factory.create("B");
        productB.productMethod();

    }
}
