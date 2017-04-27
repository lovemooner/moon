package love.moon.common.exception;

/**
 * Created by lovemooner on 2017/4/25.
 */
public enum MyExceptionType {

    UserNotFound(10002L, "User not found.");

    private long code;
    private String message;

    MyExceptionType(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
