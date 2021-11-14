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
            Object object = joinPoint.proceed();
            connection.commit();
            return object;
        }catch (RuntimeException runtimeException){
            connection.rollback();
            return runtimeException;
        }catch (Exception exception){
            connection.commit();
            return exception;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
