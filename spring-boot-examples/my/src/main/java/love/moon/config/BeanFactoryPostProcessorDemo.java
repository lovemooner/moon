package love.moon.config;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryPostProcessorDemo implements BeanFactoryPostProcessor{

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("******调用BeanFactoryPostProcessor开始");
        //获取到Spring中所有的beanName
        String[] beanStr = beanFactory.getBeanDefinitionNames();
        //循环bean做出自定义的操作
        for (String beanName : beanStr) {
//            System.out.println("bean name:"+beanName);
            if ("beanLifeCycle".equals(beanName)) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
//                beanDefinition.getPropertyValues().add("age", "20");
                System.out.println("postProcessBeanFactory");
            }
        }
//        System.out.println("******调用BeanFactoryPostProcessor结束");
    }
}