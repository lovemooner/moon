package love.moon.design.behavioral.chain.demo1;

/**
 * @auther lovemooner
 * @date 2019/11/23 19:10
 * @describe
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}