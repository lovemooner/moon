package love.moon.spring.controller;

import love.moon.spring.model.User;
import love.moon.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/9 11:18
 */
@Controller
@RequestMapping("/")
public class DemoController {


    @Autowired
    private UserService userService;


    @RequestMapping("/home")
    public String home(){
//        if(1==1){
//            throw new NullPointerException("he");
//        }
        return "index";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public List<User> user(){
        return userService.getAllUsernames();
    }
}
