package love.moon.common.exception;

/**
 * Author: lovemooner
 * Date: 2017/10/13 16:08
 */

public class BaseException extends RuntimeException {

    MessageCode type;

    public MessageCode getType() {
        return type;
    }

    public BaseException(MessageCode type) {
        this.type = type;
    }

}

