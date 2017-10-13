package love.moon.exception;

import love.moon.common.exception.DaoException;
import love.moon.common.exception.ExceptionType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Author: lovemooner
 * Date: 2017/10/13 17:14
 */
public class TestDao {

    public String sayHello() {
        InputStream is;
        URL url=null;
        try {
            is = url.openStream();
        } catch (IOException e) {
            throw new DaoException(ExceptionType.UserNotFound);
        }
        return "Hi";
    }
}
