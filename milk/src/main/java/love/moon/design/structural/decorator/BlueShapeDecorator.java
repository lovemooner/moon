package love.moon.design.structural.decorator;

import love.moon.design.structural.facade.Shape;

/**
 * @auther lovemooner
 * @date 2019/11/20 16:27
 * @describe
 */
public class BlueShapeDecorator extends AbsDecorator {

    public BlueShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("Border Color: Blue");
    }
}