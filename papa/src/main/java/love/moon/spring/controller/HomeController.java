package love.moon.spring.controller;

import love.moon.common.PageResult;
import love.moon.spring.common.ServiceException;
import love.moon.spring.dao.HomeIPDto;
import love.moon.spring.model.HomeIP;
import love.moon.spring.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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


//    @ResponseBody
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public String upload1(HttpServletRequest request, HttpServletResponse response,
//                          MultipartFile files) throws IOException {
//        System.out.println("upload");
////        String fileName = files[0].getOriginalFilename();
////
////        String path="C:\\Users\\nadong\\Desktop\\"+fileName;
////        uploadFile.transferTo(new File(path));
//        return "aa";
//    }


}
