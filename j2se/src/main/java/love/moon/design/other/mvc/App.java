package love.moon.design.other.mvc;

/**
 * @auther lovemooner
 * @date 2019/11/20 14:58
 * @describe
 */
public class App {

    public static void main(String[] args) {
        Student model  = getFromDB();
        //创建一个视图：把学生详细信息输出到控制台
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
        //更新模型数据
        controller.setStudentName("John");
        controller.updateView();
    }

    private static Student getFromDB(){
        Student student = new Student();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }
}
