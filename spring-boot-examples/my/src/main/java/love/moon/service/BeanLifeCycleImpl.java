package love.moon.service;

import love.moon.annotation.Annotation100;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanLifeCycleImpl  implements InitializingBean {


    public BeanLifeCycleImpl() {
        System.out.println("construtor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean--afterPropertiesSet");
    }

    @PostConstruct //初始化方法的注解方式  等同与init-method=init
    public void init() {
        System.out.println("init-method");
    }

    @PreDestroy    //销毁方法的注解方式  等同于destory-method=destory222
    public void destory(){
        System.out.println("调用销毁化方法....");
    }

    @Annotation100(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public void doTest(){
        System.out.println("doTest");

    }


}
