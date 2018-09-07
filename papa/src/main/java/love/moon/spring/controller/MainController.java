package love.moon.spring.controller;

import love.moon.spring.model.FundDTO;
import love.moon.spring.model.User;
import love.moon.spring.service.IFundService;
import love.moon.spring.service.IShoppingCartService;
import love.moon.spring.service.IUserService;
import love.moon.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
@Controller
@RequestMapping("/test")
public class MainController {
    private Logger LOG = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    IFundService fundService;

    String[] yes = {"YES", "NO"};


    @RequestMapping(value = "/getFundData", method = RequestMethod.GET)
    @ResponseBody
    public FundDTO mockHand() {
        return fundService.mockHand();
    }

    @RequestMapping(value = "/initFundData", method = RequestMethod.GET)
    @ResponseBody
    public Object initFundData() {
        fundService.initData();
        return "good-initFundData";
    }

    @RequestMapping(value = "/mockBlockRequest", method = RequestMethod.GET)
    @ResponseBody
    public String mockBlockRequest() {
        String url = "http://localhost:8001/test";
        String result = HttpUtil.sendGet(url, null);
        System.out.println(result);
        return "Back"+result;
    }


    @RequestMapping("")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<User> user() {
        LOG.info("User...");
        return userService.getAllUsernames();
    }

}
