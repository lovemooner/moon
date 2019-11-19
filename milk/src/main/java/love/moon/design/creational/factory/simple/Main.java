package love.moon.design.creational.factory.simple;


import love.moon.design.creational.factory.IBusiness;

public class Main {

    public static void main(String[] args) {
        IBusiness business1 = Factory.create("A");
        business1.doService();
    }
}
