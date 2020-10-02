package love.moon.algorithm.ratelimit.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author dongnan
 * @date 2020/9/24 15:19
 */
public class RateLimiterMain {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(2);
        System.out.println("h1");
        System.out.println(rateLimiter.acquire(5));//阻塞获取
        System.out.println("h2");
        System.out.println(rateLimiter.acquire(2));
        System.out.println("h3");
        System.out.println(rateLimiter.acquire(1));
        System.out.println("h4");
    }
}
