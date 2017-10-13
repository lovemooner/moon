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

    public MyException(ExceptionType type) {
        this(type.getMessage());
        this.type = type;
    }

    ExceptionType type;

    public ExceptionType getType() {
        return type;
    }
}
