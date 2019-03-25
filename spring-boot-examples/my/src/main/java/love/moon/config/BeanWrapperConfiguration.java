package love.moon.config;

import love.moon.annotation.Annotation100;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

@Configuration
public class BeanWrapperConfiguration {

    @Bean
    public BeanPostProcessor entityManagerBeanPostProcessor() {

        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                final Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
                for (Method method : methods) {
                    Annotation100 annotation = AnnotationUtils.findAnnotation(method, Annotation100.class);
                    if (Objects.nonNull(annotation)) {
                        InvocationHandler100 handler100 = new InvocationHandler100();
                        InvocationHandler handler = Proxy.getInvocationHandler(bean);
                        handler100.setDelegate(handler);
                        Class<?> clazz = bean.getClass();
                        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler100);
                    }
                }
                return bean;
            }
        };
    }
}
