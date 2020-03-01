package love.moon.load.jload.bean;

/**
 * Author: lovemooner
 * Date: 2018/8/23 13:20
 */
public class Result {
    private long requestTime;
    private long respondTime;
    private boolean isSuccess;


    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getRespondTime() {
        return respondTime;
    }

    public void setRespondTime(long respondTime) {
        this.respondTime = respondTime;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

}
