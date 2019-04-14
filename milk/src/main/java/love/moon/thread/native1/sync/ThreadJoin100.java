package love.moon.thread.native1.sync;

public class ThreadJoin100 {

    public static void main(String[] args) throws InterruptedException {
        //循环五次
        for (int i = 0; i < 5; i++) {

            MyThread thread = new MyThread();
            //启动线程
            thread.start();
            thread.join();
            System.out.println("主线程执行完毕");
            System.out.println("~~~~~~~~~~~~~~~");

        }
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程执行完毕");

        }
    }
}
