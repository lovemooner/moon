package love.moon.spring.service;

import love.moon.spring.dao.UserDAO;
import love.moon.spring.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
@Component
public class UserServiceImpl implements UserService {
    private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDAO userDao;

    public void saveUsers(List<User> us) {
        for (User u : us) {
            userDao.save(u);
        }
    }

    public void test() {
    }

    public void testAopExposeProxy(){
        ((UserServiceImpl) AopContext.currentProxy()).test();
    }

    public List<User> getAllUsernames() {
        test();
        return userDao.findAll();
    }

    public String sayHello(String words){
        return "Hi"+words;
    }
}