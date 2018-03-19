package eu.dnb.openbanking.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by rmang on 18-03-2018.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(eu.dnb.openbanking.controller..*) || within(eu.dnb.openbanking.service..*)")
    public void loggingPointCut() {

    }

    @Around("loggingPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("Entering into {}.{} with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            logger.debug("Exiting from {}.{} with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            return result;
        } catch (Exception ex) {
            logger.error("Error in {}.{}, exception = {} ", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), ex.getMessage());
            throw ex;
        }
    }

}
