package love.moon.propagation;

import love.moon.spring.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: lovemooner
 * Date: 2017/6/21 10:13
 */
@Service
public class InvokeServiceA {


    @Autowired
    private ComDao comDao;

    @Transactional(propagation= Propagation.NESTED,rollbackFor = {ServiceException.class})
    public void jdbcExceptionInsert() throws ServiceException {
        comDao.jdbcExceptionInsertTest();
    }
}
