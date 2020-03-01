package love.moon.design.creational.prototype.simple;

/**
 * 抽象原型角色
 * @auther lovemooner
 * @date 2019/11/20 9:52
 * @describe
 */
public abstract class Prototype {
    private String name;

    public Prototype(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 克隆自身的方法
     * @return 一个从自身克隆出来的对象。
     */
    public abstract Prototype clone();
}
