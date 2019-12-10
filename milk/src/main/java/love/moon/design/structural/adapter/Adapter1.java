package love.moon.design.structural.adapter;

/**
 * @auther lovemooner
 * @date 2019/11/19 10:55
 * @describe
 */
public class Adapter1 extends Adaptee implements ITarget{

    public void request() {
        System.out.println("request");
        this.specificRequest();
    }
}