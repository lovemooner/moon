package love.moon.design.structural.facade;

/**
 * @auther lovemooner
 * @date 2019/11/20 15:31
 * @describe
 */
public class App {

    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
