package love.moon.algorithm.ratelimit.ratelimiter;


import com.google.common.util.concurrent.RateLimiter;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author dongnan
 * @date 2020/9/24 15:08
 */
public class RateLimiterExample {

    //Guava  0.5的意思是 1秒中0.5次的操作，2秒1次的操作  从速度来限流，从每秒中能够执行的次数来
    private final static RateLimiter rt=RateLimiter.create(0.5d);


    private static void testLimiter(){
        rt.acquire();// 获取 1 个令牌
        System.out.println("正常执行方法，ts:" + Instant.now());
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0,10).forEach((i)->{
            //RateLimiterExample::testLimiter 这种写法是创建一个线程
            service.submit(RateLimiterExample::testLimiter);
        });
    }
}
