package love.moon.design.structural.decorator;

import love.moon.design.structural.facade.Circle;
import love.moon.design.structural.facade.Rectangle;
import love.moon.design.structural.facade.Shape;

/**
 * @auther lovemooner
 * @date 2019/11/20 16:06
 * @describe
 */
public class App {

    public static void main(String[] args) {
        Shape circle = new Circle();
        circle.draw();

        AbsDecorator redCircle = new RedShapeDecorator(new Circle());
        redCircle.draw();
        AbsDecorator blueCircle = new BlueShapeDecorator(new Circle());
        blueCircle.draw();

        AbsDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        redRectangle.draw();
    }
}
