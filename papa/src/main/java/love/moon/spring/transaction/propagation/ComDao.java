package love.moon.spring.transaction.propagation;

import love.moon.spring.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/6/21 10:15
 */
@Repository
public class ComDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void jdbcExceptionInsertTest() throws ServiceException {
        Random random = new Random();
        jdbcTemplate.execute("insert into nan_student(id,name) values ("+random.nextInt(10000)+",'excepNor')");
//        jdbcTemplate.execute("insert into nan_student(3id,name) values ("+random.nextInt(10000)+",'exception')");
        if(1==1) throw new RuntimeException("jdbcExceptionInsertTest");
    }

    public void jdbcValidInsertTest(){
        Random random = new Random();
        jdbcTemplate.execute("insert into nan_student(id,name) values ("+random.nextInt(10000)+",'valid')");
    }

}
