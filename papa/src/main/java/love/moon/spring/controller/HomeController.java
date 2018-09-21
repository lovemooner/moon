package love.moon.spring.controller;

import love.moon.common.PageResult;
import love.moon.spring.common.ServiceException;
import love.moon.spring.dao.HomeIPDto;
import love.moon.spring.model.HomeIP;
import love.moon.spring.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IHomeService homeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageResult list(HttpServletRequest request) throws ServiceException, InterruptedException {
        List<HomeIP> ips = homeService.getHomeIps(0, 500);
        if (ips != null & !ips.isEmpty()) {
            List<HomeIPDto> dtoList = new ArrayList<HomeIPDto>();
            for (HomeIP homeIP : ips) {
                dtoList.add(homeIP.toDTO());
            }
            PageResult result = new PageResult(dtoList);
            result.setTotalRows(homeService.countHomeIps());
            return result;
        }
        return null;
    }

}
