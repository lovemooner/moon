package love.moon.load.jload.bean;

/**
 * Author: lovemooner
 * Date: 2018/8/23 16:49
 */
public class LoadCache {

    private volatile int samples; //TODO AtomicInteger to replace?
    private volatile int errorSamples;
    private volatile long  minResponseTime;
    private volatile long  maxResponseTime;
    private volatile long totalResponseTime;

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public int getErrorSamples() {
        return errorSamples;
    }

    public void setErrorSamples(int errorSamples) {
        this.errorSamples = errorSamples;
    }

    public long getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(long minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public long getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(long maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public long getTotalResponseTime() {
        return totalResponseTime;
    }

    public void setTotalResponseTime(long totalResponseTime) {
        this.totalResponseTime = totalResponseTime;
    }
}
