package love.moon.design.behavioral.strategy;

/**
 * @auther lovemooner
 * @date 2019/11/19 17:45
 * @describe
 */
public class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
