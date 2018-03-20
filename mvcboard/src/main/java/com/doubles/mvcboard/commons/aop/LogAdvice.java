package com.doubles.mvcboard.commons.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Around("execution(* com.doubles.mvcboard..*Controller.*(..))"
            + " or execution(* com.doubles.mvcboard..service..*Impl.*(..))"
            + " or execution(* com.doubles.mvcboard..persistence..*Impl.*(..))")
    public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String name = "";

        if (type.contains("Controller")) {
            name = "Controller : ";
        } else if (type.contains("Service")) {
            name = "Service : ";
        } else if (type.contains("DAO")) {
            name = "Persistence : ";
        }

        long end = System.currentTimeMillis();

        logger.info(name + type + "."+proceedingJoinPoint.getSignature().getName() + "()");
        logger.info("Argument/Parameter : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        if (result != null) {
            logger.info("Return Value : " + result.toString());
        } else {
            logger.info("Return Type : void");
        }
        logger.info("Running Time : " + (end-start));
        logger.info("----------------------------------------------------------------");

        return result;
    }

}
