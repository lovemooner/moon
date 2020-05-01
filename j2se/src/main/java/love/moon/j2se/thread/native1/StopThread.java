package love.moon.j2se.thread.native1;

/**
 * @author
 */

public class StopThread extends Thread {

    private int i = 0;
    private int j = 0;

    @Override
    public void run() {
            ++i;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++j;

    }


    public void print() {
        System.out.println("i=" + i + " j=" + j);
    }

    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        // 休眠 1 秒，确保 i 变量自增成功
        Thread.sleep(1000);
        // 暂停线程
        thread.stop(); // 错误的终止
//        thread.interrupt(); // 错误的终止
        while (thread.isAlive()) {
            // 确保线程已经终止
        } // 输出结果
        thread.print();
    }

}