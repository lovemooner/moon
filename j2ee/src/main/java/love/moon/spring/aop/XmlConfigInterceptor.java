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
public class XmlConfigInterceptor {
    private Logger LOG = LoggerFactory.getLogger(XmlConfigInterceptor.class);

    public void doBeforeTask(){
        LOG.debug("doBeforeTask");
    }

    public void doAfterTask(){
        LOG.debug("doAfterTask");
    }

    public void doAfterReturnningTask(Object retVal){
        LOG.debug("doAfterReturnningTask:{}",retVal);
    }

    public Object doAroundTask(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Long current = System.currentTimeMillis();
            Object returnVal = pjp.proceed();
            LOG.info("Service :{}-->用时:{} ms",pjp.getSignature().getDeclaringType().getName() + "." + pjp.getSignature().getName(), System.currentTimeMillis() - current);
            return returnVal;
        } catch (Throwable throwable) {
            throw  throwable;
        }
    }

}
