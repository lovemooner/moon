package love.moon.exception;

import love.moon.common.exception.DaoException;
import love.moon.common.exception.ExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 测试方法里是否要catch异常，日志显示的区别
 * Author: lovemooner
 * Date: 2017/5/3 14:53
 */
public class Demo {
    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);

    public void testCheckException() {
        try {
            throw new SQLException();
        } catch (SQLException e) {
            throw new DaoException(ExceptionType.UserNotFound);
        }
    }

    public void testUnCheckException() throws RuntimeException{
        System.out.println("testUnCheckException");
    }

    public void testUnCheckException(int val) {
        if (val == 1) {
            throw new NullPointerException();
        }
    }

    public void test2() {
        Demo demo = new Demo();
        demo.testCheckException();
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        try {
            demo.testUnCheckException(2);
        } catch (DaoException e) {
            LOG.info(e.getType().toString());
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
