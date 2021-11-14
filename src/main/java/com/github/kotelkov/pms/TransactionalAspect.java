package com.github.kotelkov.pms;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
public class TransactionalAspect {

    @Autowired
    private Connection connection;

    @Around("@annotation(com.github.kotelkov.pms.annotation.Transactional)")
    public Object runInTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            connection.setAutoCommit(false);
            joinPoint.proceed();
            connection.commit();
        }catch (RuntimeException runtimeException){
            connection.rollback();
        }catch (Exception exception){
            connection.commit();
        }finally {
            connection.setAutoCommit(true);
        }
        return null;
    }
}
