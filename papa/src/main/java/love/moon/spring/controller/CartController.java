package love.moon.spring.controller;

import love.moon.spring.model.User;
import love.moon.spring.service.CartService;
import love.moon.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: lovemooner
 * Date: 2017/6/12 17:26
 */
@RestController
public class CartController {
    private Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    private void startThread() {
        for (int i = 0; i < 100; i++) {
            ThreadRunnable tt = new ThreadRunnable();
            Thread t = new Thread(tt);
            t.start();
        }
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        LOG.info("LOG:read to start Thread");
        System.out.println("read to start Thread");
        startThread();
        User user = new User();
        user.setUserName("userName");
//        request.getSession().setAttribute("UserName", JsonUtil.objectToJson(user));
        return "cart";
    }

    @RequestMapping(value = "/updateCart", method = RequestMethod.GET)
    public String updateCart(){
        cartService.updateCart();
        return "updateCart";
    }

    @RequestMapping(value = "/saveCart", method = RequestMethod.GET)
    public String saveCart(HttpServletRequest request) {
        String userString = request.getSession().getAttribute("UserName").toString();
        User user = JsonUtil.jsonToObj(userString, User.class);
        return "saveCart";
    }


}
