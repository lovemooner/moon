package love.moon.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: lovemooner
 * Date: 2017/10/10 14:03
 */
@Controller
@RequestMapping("/redis")
public class RedisSessionSampleController {

    @RequestMapping("/set")
    @ResponseBody
    String set(HttpServletRequest request) {
        request.getSession().setAttribute("testKey", "testValue");
        return "session:testKey=testValue";
    }

    @RequestMapping("/query")
    @ResponseBody
    String query(HttpServletRequest request) {
        Object value = request.getSession().getAttribute("testKey");
        return "Session：testKey=" + value;
    }
}
