package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/1/12 13:44
 */
class Woman implements Human{

    @Override
    public String sayHello() {
        return "woman";
    }


    public static void main(String[] args) {
        Human woman = new Woman();
        woman.sayHello();

        Woman woman1=new Woman();
        woman1.sayHello();
    }


}