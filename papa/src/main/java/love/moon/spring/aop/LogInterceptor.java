package love.moon.spring.aop;

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
    private void businessService() {}

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
            Long current = System.currentTimeMillis();
            LOG.info(pjp.getSignature().getDeclaringType().getName() + ":" + pjp.getSignature().getName() + " start");
            Object returnVal = pjp.proceed();
            LOG.info(pjp.getSignature().getDeclaringType().getName() + ":" + pjp.getSignature().getName() + " end,AOP_END:用时:{} ms ",System.currentTimeMillis() - current);
            return returnVal;
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
