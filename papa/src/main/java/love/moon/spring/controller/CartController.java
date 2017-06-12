package love.moon.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: lovemooner
 * Date: 2017/6/12 17:26
 */
@RestController
public class CartController {

    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    public String cart(){
        return null;
    }
}
