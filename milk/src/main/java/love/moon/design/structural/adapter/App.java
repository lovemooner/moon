package love.moon.design.structural.adapter;

/**
 * @auther dongnan
 * @date 2019/11/19 10:57
 * @describe
 */
public class App {

    public static void main(String[] args) {
        ITarget target = new Adapter1();
        target.request();

        Adapter2 adapter2 = new Adapter2(new Adaptee());
        adapter2.request();
    }

}
