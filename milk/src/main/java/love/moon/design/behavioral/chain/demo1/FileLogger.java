package love.moon.design.behavioral.chain.demo1;

/**
 * @auther dongnan
 * @date 2019/11/23 19:10
 * @describe
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
