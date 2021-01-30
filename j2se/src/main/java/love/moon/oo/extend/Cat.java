package love.moon.oo.extend;

import org.junit.Test;

/**
 * @author : ndong
 * date : 2021/1/14 16:34
 * desc :
 */
public class Cat extends  Animal {


    public void foo() {
        System.out.println("Cat foo");
    }
    public void dosth(){
        System.out.println("Cat dosth");
    }

    @Test
    public void call(){
        Animal t = new Cat(); //向上转型，即父类引用指向子类对象，此时子类对象的类型为父类的类型
        t.foo();
//        t.dosth();//编译错误
//        t = (Cat)t;
//        t.dosth();//编译错误
        ((Cat) t).dosth();//编译成功
    }


    @Test
    public void cast(){
        Animal animal=new Cat();
        Cat cat=(Cat) animal;
        //Exception: cannot be cast,将父类转换为子类之前，应该用instanceof检查。
        Animal animal1=new Animal();
        System.out.println(animal instanceof Cat);
        System.out.println(animal1 instanceof Cat);
        Cat cat1 = (Cat)animal1;
        System.out.println("end");
    }

    public static void main(String[] args) {

    }

}
