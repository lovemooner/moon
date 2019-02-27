package love.moon.design.factory.simple;


import love.moon.design.factory.IBusiness;

public class Main {


    public void service() {
        IBusiness business1 = Factory.create("A");
        business1.doService();
    }

    public static void main(String[] args) {
        Main main=new Main();
        main.service();
    }
}
