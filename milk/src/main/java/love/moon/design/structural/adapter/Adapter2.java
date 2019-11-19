package love.moon.design.structural.adapter;

/**
 * 委托模式
 *
 * @auther dongnan
 * @date 2019/11/19 10:56
 * @describe
 */
public class Adapter2 {

    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        System.out.println("使用适配器");
        this.adaptee.specificRequest();
    }
}
