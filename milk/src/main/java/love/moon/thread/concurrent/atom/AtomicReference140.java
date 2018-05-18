package love.moon.thread.concurrent.atom;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Author: lovemooner
 * Date: 2017/10/17 14:29
 */
public class AtomicReference140 {

    public static void main(String[] args) {
        AtomicReference140 demo = new AtomicReference140();
        demo.test1();
    }

    public void test1() {
        User user1 = new User("A", 20);
        User user2 = new User("B", 30);
        AtomicReference<User> atom = new AtomicReference<User>();
        System.out.println("get：" + atom.get());

        System.out.println("getAndSet:" + atom.getAndSet(user2));
        System.out.println("调用后值变为：" + atom);

        System.out.println("compareAndSet:" + atom.compareAndSet(user2, user1));
        System.out.println("调用后值为：" + atom);
    }


    class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "(" + name + " : " + age + ")";
        }
    }
}
