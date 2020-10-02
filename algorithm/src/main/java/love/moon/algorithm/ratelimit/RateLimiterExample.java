package love.moon.algorithm.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.time.Instant;

/**
 * 令牌算法
 * @author dongnan
 * @date 2020/9/24 11:12
 */
public class RateLimiterExample {

    public static void main(String[] args) {
        // 每秒产生 10 个令牌（每 100 ms 产生一个）
        RateLimiter rt = RateLimiter.create(10);
        for (int i = 0; i <= 10; i++) {
            new Thread(() -> {
                // 以阻塞的方式获取 1 个令牌，也可以通过tryAcquire非阻塞获取
                rt.acquire();
                System.out.println("正常执行方法，ts:" + Instant.now());
            }).start();
        }
    }

}
