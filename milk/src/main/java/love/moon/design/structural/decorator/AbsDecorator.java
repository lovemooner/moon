package love.moon.design.structural.decorator;

import love.moon.design.structural.facade.Shape;

/**
 * @auther lovemooner
 * @date 2019/11/20 16:04
 * @describe
 */
public abstract class AbsDecorator implements Shape {
    protected Shape decoratedShape;

    public AbsDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}