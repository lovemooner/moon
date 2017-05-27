package love.moon.spring.controller.oracle;

import love.moon.spring.model.Student;
import love.moon.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/23 17:30
 */
@Controller
@RequestMapping("/oracle")
public class OracleController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getStudent() {
        List<Student> students = studentService.getStudent();
        return students;
    }
}
