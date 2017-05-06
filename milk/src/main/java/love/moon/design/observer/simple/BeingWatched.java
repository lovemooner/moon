package love.moon.design.observer.simple;

/**
 * Created by ndong on 2017/5/6.
 */
class BeingWatched extends java.util.Observable {

    void counter(int period) {
        for (; period > 0; period--) {
            setChanged();
            notifyObservers(new Integer(period));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupeted");
            }
        }
    }

    public static void main(String[] args) {
        BeingWatched beingWatched = new BeingWatched();//受查者
        Watcher watcher1 = new Watcher("watcher1");//观察者
        Watcher watcher2 = new Watcher("watcher2");//观察者
        beingWatched.addObserver(watcher1);
        beingWatched.addObserver(watcher2);
        beingWatched.counter(2);
    }
};
