package love.moon.design.behavioral.strategy;

/**
 * @auther dongnan
 * @date 2019/11/19 17:45
 * @describe
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
