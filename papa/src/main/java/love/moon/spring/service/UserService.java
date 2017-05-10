package love.moon.spring.service;


import love.moon.spring.model.User;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
public interface UserService {
    void saveUsers(List<User> us);

    List<User> getAllUsernames();

    String sayHello(String words);
}
