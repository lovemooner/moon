package love.moon.mybatis.dao;

import love.moon.mybatis.MyBatisUtil;
import love.moon.mybatis.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoImpl extends MyBatisUtil implements UserDao {

    public List<User> selectAllUsers() {
        SqlSession ss = openSession();
        List<User> list = ss.selectList("UserMapper.selectAllUsers");
        ss.close();
        return list;
    }

    public int insertUser(User user) {
        SqlSession session = openSession();
        int i = session.insert("UserMapper.insertUser", user);
        session.commit();
        session.close();
        return i;
    }
}
