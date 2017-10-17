package love.moon.common.exception;

/**
 * Author: lovemooner
 * Date: 2017/10/13 16:07
 */
public class DaoException extends BaseException {

    private static final long serialVersionUID = -3711290613973933714L;

    MessageCode type;

    public DaoException(MessageCode type) {
        super(type);
    }


    public MessageCode getType() {
        return type;
    }


}

