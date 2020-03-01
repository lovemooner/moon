package love.moon.spring.template.service;

import love.moon.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("service")
public class Service {

    @Autowired
   private Dao dao;

    public void testExecute(){
           dao.testExecute();
    }

    public User getUser(Long id){
        return dao.getUser(id);
    }


}
