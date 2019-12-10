package love.moon.design.creational.prototype.simple;

/**
 * @auther lovemooner
 * @date 2019/11/20 10:30
 * @describe
 */
public class Client {

    public static void main(String[] args) {
        Prototype prototype= new  ConcretePrototype1("1");
        Prototype Cloned= prototype.clone();
    }

}
