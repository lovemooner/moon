package love.moon.spring.aop;

import love.moon.spring.config.datasource.DatabaseContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceInterceptor {


    @Before("execution(* love.moon.spring.controller.mysql.*.*(..))")
    public void setDataSourceMysql(JoinPoint jp) {
        DatabaseContextHolder.setCustomerType("dataSourceMySql");
    }

    @Before("execution(* love.moon.spring.controller.oracle.*.*(..))")
    public void setDataSourceOracle(JoinPoint jp) {
        DatabaseContextHolder.setCustomerType("dataSourceOracle");
    }
}