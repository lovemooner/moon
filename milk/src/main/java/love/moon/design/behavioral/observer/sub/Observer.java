package love.moon.design.behavioral.observer.sub;

/**
 * @auther lovemooner
 * @date 2019/11/23 21:33
 * @describe
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
