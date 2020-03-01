package love.moon.j2se.thread.native1;

public class Join100 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true){
//                for (int i = 5; i < 0; i++) {
                    System.out.println("good");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i++>5){
                        break;
                    }
                }
            }
        });
        t.start();
        t.join();
        System.out.println("end");
    }
}
