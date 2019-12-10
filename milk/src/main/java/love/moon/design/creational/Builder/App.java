package love.moon.design.creational.Builder;

/**
 * @auther lovemooner
 * @date 2019/11/19 16:43
 * @describe
 */
public class App {

    public static void main(String[] args) {
        AbsBuilder  builder = new ConcreteBuilder(); //可通过配置文件实现
        Director director = new  Director(builder);
        Product product = director.construct();
    }
}
