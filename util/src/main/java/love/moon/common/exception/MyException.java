package love.moon.common.exception;

/**
 * Created by lovemooner on 2017/4/25.
 */
public class MyException extends Exception {
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(MessageCode type) {
        this(type.getMessage());
        this.type = type;
    }

    MessageCode type;

    public MessageCode getType() {
        return type;
    }
}
