package org.javaee.database;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JdbcAspect {

    @Pointcut("execution(* org.javaee.database.JDBC.*(..))")
    public void service(){}

    @Before("service()")
    public void beforeService(){

    }

    @After("service()")
    public void afterService(){

    }
}
