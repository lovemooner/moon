package love.moon.spring.dao;



import love.moon.spring.model.User;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
public interface UserDAO {
    public int save(User u);
    public List<User> findAll();
}
