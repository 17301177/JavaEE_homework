package org.javaee.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
public class JdbcAspect {

    //以注册为例子，在User表中插入一条记录
    //异常处理逻辑：
    // 如果正常运作，将connection对象返回
    // 如果发生异常，返回null
    @Pointcut("execution(* org.javaee.service.*.*(..))")
    public void service(){}

    @Around("service()")
    public void aroundService(ProceedingJoinPoint jp) throws Throwable {
        //已经将Hikari配置为默认不自动提交
        //不用做前置操作

        Connection connection = (Connection)jp.proceed();

        //对结果进行判断
        if(null != connection){
            System.out.println("插入记录成功，事务提交");
            connection.commit();
        }
        else{
            System.out.println("插入记录失败，事务回滚");
            connection.rollback();
        }
    }
}
