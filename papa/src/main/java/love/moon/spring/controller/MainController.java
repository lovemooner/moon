package love.moon.spring.controller;

import love.moon.common.HttpResponse;
import love.moon.spring.model.FundDTO;
import love.moon.spring.model.User;
import love.moon.spring.service.IFundService;
import love.moon.spring.service.IMockService;
import love.moon.spring.service.IShoppingCartService;
import love.moon.spring.service.IUserService;
import love.moon.util.HttpUtil;
import love.moon.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
    @Autowired
    private  IMockService mockService;

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

    @RequestMapping(value = "/maxThreadNum", method = RequestMethod.GET)
    @ResponseBody
    public String maxThreadNum() {

        final IMockService mockService1=mockService;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mockService1.maxThreadNum();
            }
        }).start();
        String result = "Success";
        return "maxThreadNum response:" + result;
    }

    @RequestMapping(value = "/mockBlockRequest1", method = RequestMethod.GET)
    @ResponseBody
    public String mockBlockRequest1(@PathVariable int param) throws IOException {
        String url = "http://192.168.1.107:8001/test";
        HttpResponse response = HttpUtil.sendGet(url);
        System.out.println(response.getContent());
        return "mockBlockRequest1 response:" + response.getContent();
    }

    @RequestMapping(value = "/mockBlockRequest2/{param}", method = RequestMethod.GET)
    @ResponseBody
    public String mockBlockRequest2(@PathVariable final int param) {
        final IMockService mockService1=mockService;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mockService1.mockBlockRequest2(param);
            }
        }).start();
        return "mockBlockRequest2 Response";
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
