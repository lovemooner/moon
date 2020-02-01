package love.moon.design.structural.facade;

/**
 * @auther lovemooner
 * @date 2019/11/20 15:24
 * @describe
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
