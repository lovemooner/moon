package love.moon.design.behavioral.chain.demo1;

/**
 * @auther dongnan
 * @date 2019/11/23 19:09
 * @describe
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
