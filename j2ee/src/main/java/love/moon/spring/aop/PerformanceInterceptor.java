package love.moon.spring.aop;

import love.moon.common.exception.AppException;
import love.moon.spring.common.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Author: lovemooner
 * Date: 2018/8/25 0:59
 */
@Aspect
@Component
public class PerformanceInterceptor {
    private Logger LOG = LoggerFactory.getLogger(PerformanceInterceptor.class);

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
    public Object doAroundTask(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Long current = System.currentTimeMillis();
            Object returnVal = pjp.proceed();
            LOG.info("Controller:{}-->用时:{} ms ",pjp.getSignature().getDeclaringType().getName() + "." + pjp.getSignature().getName(), System.currentTimeMillis() - current);
            return returnVal;
        } catch (Throwable throwable) {
            throw  throwable;
        }
    }
}
