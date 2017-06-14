package love.moon.spring.controller;

import love.moon.spring.model.Cart;
import love.moon.spring.model.User;
import love.moon.spring.service.CartStatus;
import love.moon.spring.service.ShoppingCartService;
import love.moon.spring.service.UserService;
import love.moon.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

/**
 * Created by nadong on 2017/4/20.
 */
@Controller
@RequestMapping("/test")
public class MainController {
    private Logger LOG = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService cartService;

    Random random = new Random();
    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String[] yes = {"YES", "NO"};

    private String randomChars(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(random.nextInt(52)));
        }
        return sb.toString();
    }

    @RequestMapping(value = "/testCart",method = RequestMethod.GET)
    @ResponseBody
    public Object cart(){

        Cart cart = new Cart();
        cart.setStatus(CartStatus.toArray()[random.nextInt(4)]);
        cart.setDiscount(yes[random.nextInt(2)]);
        cart.setSummary(randomChars(15));
         return "good";
    }

    @RequestMapping("")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public List<User> user(){
        LOG.info("User...");
         return userService.getAllUsernames();
    }

}
