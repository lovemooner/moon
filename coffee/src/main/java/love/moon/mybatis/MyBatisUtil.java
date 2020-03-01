package love.moon.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtil {

    protected static SqlSessionFactory sessionFactory;
    protected static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis/config.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected SqlSession openSession() {
        return sessionFactory.openSession();
    }
}
