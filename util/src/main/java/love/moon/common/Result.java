package love.moon.common;

/**
 * Created by lovemooner on 2017/4/25.
 */
public class Result {
    private String status;
    private int msgCode;
    private String message;
    private boolean success = true;
    private Object debug = null;

    public Result() {
        super();
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDebug() {
        return debug;
    }

    public void setDebug(Object debug) {
        this.debug = debug;
    }
}
