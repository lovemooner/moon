package love.moon.thread.object;

public class Synchronized102 {

    public synchronized void test1(){
        System.out.println(Thread.currentThread().getName()+" in");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" out");
    }

    public synchronized void test2(){
        System.out.println(Thread.currentThread().getName()+" test2");
    }


    public static void main(String[] args) {
        Synchronized102 demo=new Synchronized102();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.test1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.test2();
            }
        }).start();
    }

}
