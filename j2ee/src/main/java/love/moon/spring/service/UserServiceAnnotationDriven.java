package love.moon.spring.service;//package love.moon.spring.service;
//
//import love.moon.spring.dao.UserDAO;
//import love.moon.spring.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * Author: lovemooner
// * Date: 2017/5/22 17:47
// */
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class, readOnly = true)
//@Component
//public class UserServiceAnnotationDriven {
//
//    @Autowired
//    private UserDAO userDao;
//
//    public void saveUsers(List<User> us) {
//        for (User u : us) {
//            userDao.save(u);
//        }
//    }
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public void test() {
//
//    }
//
//    /**
//     * Transaction之对public方法有效
//     */
//    private void privateMethod(){
//
//    }
//
//    public List<User> getAllUsernames() {
//        return userDao.findAll();
//    }
//}
