package love.moon.thread.native1.sync;

public class Wait100 {

    public static void main(String[] args) {
        final Wait100 wait100=new Wait100();
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    wait100.test();
                }
            }).start();
        }
    }

    private Object lock=new Object();

    public  void test()  {
        synchronized (lock) {
            System.out.println("Thread Name:"+Thread.currentThread().getName()+",start");
            try {
                lock.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread Name:"+Thread.currentThread().getName()+",end");
        }

    }


}

