package love.moon.spring.transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: lovemooner
 * Date: 2017/6/21 10:14
 */
@Component
public class InvokeServiceB {


//    @Autowired
    private ComDao comDao;

    @Transactional(propagation = Propagation.REQUIRED)
//@Transactional(propagation=Propagation.NESTED)
    public void jdbcValidInsert() {
        comDao.jdbcValidInsertTest();
    }
}
