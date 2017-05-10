package love.celery.spring.service;

import love.celery.spring.dao.UserDAO;
import love.celery.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
//@Service("userService")
//@Transactional
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    public void saveUsers(List<User> us) {
        for (User u : us) {
            userDao.save(u);
        }
    }

    public List<User> getAllUsernames() {
        return userDao.findAll();
    }
}