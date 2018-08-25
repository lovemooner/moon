package love.moon.spring.aop;

import love.moon.common.Response;
import love.moon.common.exception.BaseException;
import love.moon.common.exception.MessageCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Author: lovemooner
 * Date: 2017/10/13 17:43
 */
@Aspect
@Component
public class RPCLogInterceptor {
    private Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

    @Pointcut("execution(* love.moon.spring.controller1.*.*(..))")
    private void businessService() {
    }

    @Around("businessService()")
    public Object doAroundTask(ProceedingJoinPoint pjp) {
        try {
            Object returnVal = pjp.proceed();
            return returnVal;
        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
            if (exception instanceof BaseException) {
                BaseException baseException = (BaseException) exception;
                MessageCode type = baseException.getType();
                return new Response(type.getCode(), type.getMessage());
            } else {
                return MessageCode.toResponse(MessageCode.INTERNAL_SERVER_ERROR);
            }
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

}
