package love.moon.spring.dao;

import love.moon.spring.model.Student;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/23 17:35
 */
@Repository
public class StudentDAO {

    @Resource
    private SessionFactory sessionFactory;

    public List<Student> getStudent() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Student");
        return query.list();
    }
}

