package love.moon.spring.transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: lovemooner
 * Date: 2017/6/21 10:12
 */
@Service("invokeService")
public class InvokeService {


    @Autowired
    private InvokeServiceA serviceA;
    @Autowired
    private InvokeServiceB serviceB;


    @Transactional(propagation = Propagation.REQUIRED)
    public void jdbcInvokeTest() {
        serviceB.jdbcValidInsert();
        try {
            serviceA.jdbcExceptionInsert();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("jdbc invoke method end.");
    }

}