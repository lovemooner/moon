package love.moon.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Author: lovemooner
 * Date: 2017/5/24 13:15
 */
@Component
public class SampleInterceptor {
    private Logger LOG = LoggerFactory.getLogger(ServiceInterceptor.class);

    public void doBeforeTask(){
        LOG.debug("doBeforeTask");
    }

    public void doAfterTask(){
        LOG.debug("doAfterTask");
    }

    public void doAfterReturnningTask(Object retVal){
        LOG.debug("doAfterReturnningTask:{}",retVal);
    }

    public Object doAroundTask(ProceedingJoinPoint pjp) {
        Object returnVal = null;
        try {
            returnVal = pjp.proceed();
            LOG.info(pjp.getSignature().getDeclaringType().getName() + ":" + pjp.getSignature().getName() + " end ");
            return returnVal;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
