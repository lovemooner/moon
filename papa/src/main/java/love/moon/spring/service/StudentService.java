package love.moon.spring.service;

import love.moon.spring.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/23 17:54
 */
@Component
public interface StudentService {

    List<Student> getStudent();
}
