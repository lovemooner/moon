package love.moon.design.behavioral.strategy;

/**
 * 客户端，用调用者
 * @auther lovemooner
 * @date 2019/11/19 17:46
 * @describe
 */

public class App {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));


    }
}
