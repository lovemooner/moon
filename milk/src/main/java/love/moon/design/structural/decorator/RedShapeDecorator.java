package love.moon.design.structural.decorator;

import love.moon.design.structural.facade.Shape;

/**
 * @auther lovemooner
 * @date 2019/11/20 16:05
 * @describe
 */
public class RedShapeDecorator extends AbsDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("Border Color: Red");
    }
}
