package love.moon.spring.controller;

import love.moon.spring.common.ServiceException;
import love.moon.mock.ThreadRunnable;
import love.moon.spring.model.Cart;
import love.moon.spring.model.User;
import love.moon.spring.service.CartService;
import love.moon.spring.service.ProductService;
import love.moon.util.JsonUtil;
import love.moon.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/6/12 17:26
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    private Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

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


    @RequestMapping(value = "/list/{param}", method = RequestMethod.GET)
    @ResponseBody
    public List<Cart> list(HttpServletRequest request,@PathVariable String param) throws ServiceException, InterruptedException {
//        request.getSession().setAttribute("isLogin","true");
//        System.out.println("isLogin:"+request.getSession().getAttribute("isLogin"));
        System.out.println("Param:"+param);
        List<Cart> carts = cartService.getCarts(0, 500);
//        Thread.sleep(5000L);
        return carts;
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    @ResponseBody
    public String listCart2() throws ServiceException {
        return RandomUtil.random();
    }

    @RequestMapping(value = "/list3", method = RequestMethod.GET)
    @ResponseBody
    public String listCart3(HttpServletRequest request) throws ServiceException {
        System.out.println("ThreadName:"+Thread.currentThread().getName());
        return RandomUtil.random();
    }

    @RequestMapping(value = "/updateCart", method = RequestMethod.GET)
    public String updateCart() throws ServiceException {
        cartService.updateCart();
        return "updateCart";
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
    public String updateProduct() throws ServiceException {
        productService.updateProduct();
        return "updateProduct";
    }

    @RequestMapping(value = "/saveCart", method = RequestMethod.GET)
    public String saveCart(HttpServletRequest request) {
        String userString = request.getSession().getAttribute("UserName").toString();
        User user = JsonUtil.jsonToObj(userString, User.class);
        return "saveCart";
    }


}
