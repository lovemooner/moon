package love.moon.exception.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: lovemooner
 * Date: 2018/7/19 16:45
 */
public class ControllerMocker {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerMocker.class);

    public ApiResponse login(String name) {
        try {
            if (name == null) {
                return MessageCode.toApiResponse(MessageCode.USER_NAME_EMPITY);
            }
            return MessageCode.toApiResponse(MessageCode.LOGIN_SUCCESS);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return MessageCode.toApiResponse(MessageCode.LOGIN_EXCEPTION);
        }
    }

}


