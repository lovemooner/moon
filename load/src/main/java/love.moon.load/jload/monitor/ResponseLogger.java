package love.moon.load.jload.monitor;

import love.moon.load.jload.bean.JLoads;
import love.moon.load.jload.bean.LoadCache;
import love.moon.load.jload.bean.Result;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2018/8/26 1:20
 */
public class ResponseLogger implements IResponseLogger{

    public void log(Result result) {
        ReentrantLock lock = new ReentrantLock();
        LoadCache cache = JLoads.getLoadCache();
        try {
            lock.lock();
            if (result.isSuccess()) {
                long responseTime = result.getRespondTime() - result.getRequestTime();
                cache.setMinResponseTime(Math.min(responseTime, cache.getMinResponseTime()));
                cache.setMaxResponseTime(Math.max(responseTime, cache.getMaxResponseTime()));
                cache.setTotalResponseTime(cache.getTotalResponseTime() + responseTime);
                cache.setSamples(cache.getSamples() + 1);
            } else {
                cache.setErrorSamples(cache.getErrorSamples() + 1);
            }
        } finally {
            lock.unlock();
        }
    }
}
