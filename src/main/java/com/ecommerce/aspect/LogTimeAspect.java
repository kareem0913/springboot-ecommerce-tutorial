package com.ecommerce.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogTimeAspect {

    @Around("execution(* com.ecommerce.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed(); // must use ProceedingJoinPoint
            long executionTime = System.currentTimeMillis() - start;
            log.info("Method {} executed in {} ms", joinPoint.getSignature(), executionTime);
            return result;
        } catch (Throwable throwable) {
            log.error("Exception in method {}: {}", joinPoint.getSignature(), throwable.getMessage());
            throw throwable;
        }
    }
}
