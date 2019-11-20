package love.moon.design.structural.facade;

/**
 * 外观类 Facade
 * @auther dongnan
 * @date 2019/11/20 15:30
 * @describe
 */
public class ShapeMaker {

    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }

}
