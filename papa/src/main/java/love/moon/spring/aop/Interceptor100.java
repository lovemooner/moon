package love.moon.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class Interceptor100 {
    private Logger LOG = LoggerFactory.getLogger(Interceptor100.class);

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
    public Object doAroundTask(ProceedingJoinPoint point) {
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            Class<?> clazz = point.getTarget().getClass();
            Object[] args = point.getArgs();
            Object returnVal = point.proceed();
            return returnVal;
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
