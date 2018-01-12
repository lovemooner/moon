package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/1/12 10:54
 */
public class ConstructorSynchronized {
        public ConstructorSynchronized(){
            try {
                System.out.println(Thread.currentThread().getName() + "中创建A");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                System.out.println("A is " +new ConstructorSynchronized());
            }

        }).start();

        new Thread(new Runnable(){

            @Override
            public void run() {
                System.out.println("A is " +new ConstructorSynchronized());
            }

        }).start();
    }
}
