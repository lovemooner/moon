package love.moon.j2se.thread.concurrent.schedule;

import java.util.Timer;
import java.util.TimerTask;

public class Timer101 {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int n = 0;
            @Override
            public void run() {
                System.out.println(n);
                if (++n == 5) {
                    timer.cancel();
                }
            }
        },0,1000l);

    }
}
