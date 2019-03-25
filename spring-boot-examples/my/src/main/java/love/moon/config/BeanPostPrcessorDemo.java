package love.moon.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostPrcessorDemo implements BeanPostProcessor {
    /**
     * Bean 实例化之前进行的处理
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("beanLifeCycleImpl".equals(beanName)){
            System.out.println(beanName + "---postProcessBeforeInitialization");

        }
        return bean;
    }

    /**
     * Bean 实例化之后进行的处理
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("beanLifeCycleImpl".equals(beanName)){
            System.out.println(beanName + "---postProcessAfterInitialization");

        }
        return bean;
    }
}
