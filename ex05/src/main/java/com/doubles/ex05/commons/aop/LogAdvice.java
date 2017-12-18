package com.doubles.ex05.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Around("execution(* com.doubles.ex05..*ServiceImpl.*(..))")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("======================================logPrint() START =========================================");

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        String type = joinPoint.getSignature().getDeclaringTypeName();
        logger.info(type + "." + joinPoint.getSignature().getName() + "()");
        logger.info("Argument / Parameter : " + Arrays.toString(joinPoint.getArgs()));

        long endTime = System.currentTimeMillis();
        logger.info("Method Running Time : " + (endTime - startTime) );

        logger.info("======================================logPrint() END =========================================");

        return result;
    }

}
