package love.moon.design.creational.prototype.manager;

/**
 * @auther dongnan
 * @date 2019/11/20 10:39
 * @describe
 */
public class Client {

    public static void main(String[] args) {
        Prototype p3 = PrototypeManager.getPrototype("p1").clone();
        p3.setName("张三");
        System.out.println("第一个实例的副本：" + p3);
        Prototype p4 = PrototypeManager.getPrototype("p2").clone();
        p4.setName("李四");
        System.out.println("第二个实例的副本：" + p4);
        // 注销第一个实例
        PrototypeManager.removePrototype("p1");


    }
}
