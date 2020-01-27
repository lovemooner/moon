package love.moon.spring.Exception;

import love.moon.common.exception.AppException;
import love.moon.spring.controller.CartController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomExceptionResolver implements HandlerExceptionResolver {
    private Logger LOG = LoggerFactory.getLogger(CustomExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {

        LOG.error(e.getMessage(),e);

        ModelAndView modelAndView = new ModelAndView();
         if(e instanceof AppException) {
            AppException appException = (AppException) e;
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
            response.setCharacterEncoding("UTF-8"); //避免乱码
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                response.getWriter().write("{\"success\":false,\"msg\":\"" + e.getMessage() + "\"}");
            } catch (IOException e1) {
                LOG.error("与客户端通讯异常:"+ e.getMessage(), e);
            }

        }else if(e instanceof CustomException) {
             CustomException customException = (CustomException) e;
             modelAndView.addObject("message", customException.getMessage());
             modelAndView.setViewName("/WEB-INF/jsp/error.jsp");
         }else {
            modelAndView.addObject("message", "系统未知错误");
            modelAndView.setViewName("/WEB-INF/jsp/error.jsp");
        }

        return modelAndView;

    }
}
