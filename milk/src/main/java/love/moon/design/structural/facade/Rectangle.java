package love.moon.design.structural.facade;

/**
 * @auther lovemooner
 * @date 2019/11/20 15:23
 * @describe
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
