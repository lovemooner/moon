package love.moon.spring.service;

import love.moon.spring.dao.StudentDAO;
import love.moon.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/23 17:53
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<Student> getStudent() {
        List<Student> students = studentDAO.getStudent();
        return students;
    }

}
