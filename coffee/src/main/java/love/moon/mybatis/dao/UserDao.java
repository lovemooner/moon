package love.moon.mybatis.dao;

import love.moon.mybatis.domain.User;

import java.util.List;

public interface UserDao {

    List<User> selectAllUsers();

    int insertUser(User user);
}
