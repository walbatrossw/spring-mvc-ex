package com.doubles.ex03.commons.aop;

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

    @Before("execution(* com.doubles.ex03.service.MessageService*.*(..))")
    public void startLog(JoinPoint jp) {
        logger.info("============================ startLog() =====================================");
        logger.info(Arrays.toString(jp.getArgs()));
    }

    @Around("execution(* com.doubles.ex03..*Controller.*(..))"
            + " || execution(* com.doubles.ex03..*Service*.*(..))"
            + " || execution(* com.doubles.ex03..*DAO*.*(..))"
    )
    public Object logPrint(ProceedingJoinPoint pjp) throws Throwable {
        // Start time
        long startTime = System.currentTimeMillis();
        // 핵심로직으로 이동
        Object result = pjp.proceed();
        // Class name
        String type = pjp.getSignature().getDeclaringTypeName();
        String name = "";
        // Controller name
        if (type.contains("Controller")) {
            name = "Controller \t: ";
            // Service name
        } else if (type.contains("Service")) {
            name = "ServiceImpl \t: ";
            // DAO name
        } else if (type.contains("Dao")) {
            name = "DaoImpl \t: ";
        }
        // Method name
        logger.info(name + type + "." + pjp.getSignature().getName() + "()");
        // Argument / Parameter
        logger.info("Arguments / Parameters : " + Arrays.toString(pjp.getArgs()));
        // End time
        long endTime = System.currentTimeMillis();
        // Running time
        long runTime = endTime - startTime;
        logger.info("Method Running time : " + runTime);
        return result;
    }
}
