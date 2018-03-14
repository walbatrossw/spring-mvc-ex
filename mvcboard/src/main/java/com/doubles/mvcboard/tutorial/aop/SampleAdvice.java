package com.doubles.mvcboard.tutorial.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class SampleAdvice {

    private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

//    @Before("execution(* com.doubles.mvcboard.tutorial.service.MessageService*.*(..))")
//    public void startLog(JoinPoint joinPoint) {
//        logger.info("----------------------------------------------------------------");
//        logger.info(Arrays.toString(joinPoint.getArgs()));
//    }

    @Around("execution(* com.doubles.mvcboard.tutorial.service.MessageService*.*(..))")
    public Object timeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logger.info("Arguments : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        logger.info("MethodName : " + proceedingJoinPoint.getSignature().getName() + "()");
        logger.info("RunningTime : " + (endTime - startTime));

        return result;
    }

}
