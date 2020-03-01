package love.moon.design.creational.prototype.simple;

/**
 * @auther lovemooner
 * @date 2019/11/20 9:53
 * @describe
 */
public class ConcretePrototype1 extends Prototype {
    public ConcretePrototype1(String name) {
        super(name);
    }

    public Prototype clone() {
        Prototype prototype = new ConcretePrototype1(this.getName());
        return prototype;
    }
}
