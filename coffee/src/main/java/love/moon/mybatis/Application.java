package love.moon.mybatis;

import love.moon.mybatis.dao.UserDao;
import love.moon.mybatis.dao.UserDaoImpl;
import love.moon.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;


public class Application {


    public static void main(String[] args) throws IOException {
        UserDao userDao=new UserDaoImpl();

        User user=new User();
        user.setUserName("test");
//        userDao.insertUser(user);

       List<User> userList= userDao.selectAllUsers();
       userList.forEach(n-> System.out.println("id:"+n.getId()+",name:"+ n.getUserName()) );
    }

}
