package love.moon.design.creational.prototype.manager;

/**
 * @auther lovemooner
 * @date 2019/11/20 10:37
 * @describe
 */
public interface Prototype {

     Prototype clone();
     String getName();
     void setName(String name);

}
