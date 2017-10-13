package love.moon.spring.aop;

import love.moon.common.exception.AppException;
import love.moon.common.exception.DaoException;
import love.moon.spring.common.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Author: lovemooner
 * Date: 2017/5/23 14:11
 */
@Aspect
@Component
public class LogInterceptor {
    private Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

    @Pointcut("execution(* love.moon.spring.controller.*.*(..))")
    private void businessService() {
    }

    @AfterReturning(pointcut = "businessService()", returning = "retVal")
    public void doAfterReturnningTask(Object retVal) {
//        LOG.info("doAfterReturnningTask:{}",retVal);
    }

    @AfterThrowing(pointcut = "businessService()", throwing = "ex")
    public void doAfterThrowingTask(Exception ex) {
        LOG.info("you can intercept thrown exception here");
    }

    @Around("businessService()")
    public Object doAroundTask(ProceedingJoinPoint pjp) {
        try {
            Object returnVal = pjp.proceed();
            return returnVal;
        } catch (AppException e) {
            LOG.error(e.getMessage(), e);
            return null;
        } catch (ServiceException e) {
            LOG.error(e.getMessage(), e);
            return null;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            //send the ERROR info to DB manager
            return null;
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
