package love.moon.design.behavioral.observer.demo1;

/**
 * @author : ndong
 * date : 2020/11/4 15:09
 * desc : 观察者
 */
public interface Observer {

    /**
     * 更新接口
     * @param arg    更新的状态
     */
    void update(String arg);

}
