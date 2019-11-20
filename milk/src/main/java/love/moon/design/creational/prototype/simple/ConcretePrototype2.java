package love.moon.design.creational.prototype.simple;

/**
 * @auther dongnan
 * @date 2019/11/20 9:54
 * @describe
 */
public class ConcretePrototype2 extends Prototype {
    public ConcretePrototype2(String id) {
        super(id);
    }

    public Prototype clone() {
        Prototype prototype = new ConcretePrototype2(this.getName());
        return prototype;
    }
}

