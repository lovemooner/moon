package love.moon.j2se.thread.concurrent.schedule;

import java.util.Timer;
import java.util.TimerTask;

public class Timer100 {

    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                System.out.println("test-"+System.currentTimeMillis());
            }
        };

       //以下是几种调度task的方法：
       long delay=5000l;
//        timer.schedule(timerTask, delay);
//       Date firstTime=new Date();
        long period=1000l;
//        timer.schedule(timerTask, firstTime, period);
//
        timer.schedule(timerTask, 0, period);
    }
}
