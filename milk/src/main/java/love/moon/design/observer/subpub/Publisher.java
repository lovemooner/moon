package love.moon.design.observer.subpub;

/**
 * Created by ndong on 2017/5/6.
 */
class Publisher extends java.util.Observable {

    public void publish(int period) {
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
        Publisher publisher = new Publisher();//受查者
        Subscriber subscriber1 = new Subscriber("subscriber1");//观察者
        Subscriber subscriber2 = new Subscriber("subscriber2");//观察者
        publisher.addObserver(subscriber1);
        publisher.addObserver(subscriber2);
        publisher.publish(2);
    }
};
