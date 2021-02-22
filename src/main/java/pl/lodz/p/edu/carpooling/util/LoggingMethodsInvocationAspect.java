package pl.lodz.p.edu.carpooling.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

@Component
@Aspect
@Slf4j
public class LoggingMethodsInvocationAspect {

    @Around("execution(* pl.lodz.p.edu.carpooling.module..*(..)) || " +
            "execution(* pl.lodz.p.edu.carpooling.persistence.dao..*(..)) || " +
            "execution(* pl.lodz.p.edu.carpooling.persistence.repository..*(..))")
    public Object logMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
            
        logMessage(createBeginningOfMethodMessage(joinPoint));

        stopWatch.start();
        Object methodReturnValue = handleMethodInvocation(joinPoint);
        stopWatch.stop();

        logMessage(createEndingOfMethodMessage(joinPoint, stopWatch.getTotalTimeMillis()));

        return methodReturnValue;
    }

    private Object handleMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object methodReturnValue;
        try {
            methodReturnValue = joinPoint.proceed();
        } catch (ObjectOptimisticLockingFailureException ex) {
            log.error("ObjectOptimisticLockingFailureException occurred for: " + ex.getPersistentClassName() + ", id=[" + ex.getIdentifier() + "]");
            throw BaseAppException.createOptimisticLockException();
        } catch (Exception e) {
            log.error("Unexpected exception occurred: " + e.getMessage());
            throw BaseAppException.createUnexpectedException();
        }
        return methodReturnValue;
    }
    
    private void logMessage(String message) {
        log.info(message);
    }
    
    private String createBeginningOfMethodMessage(ProceedingJoinPoint joinPoint) {
        return createPIDNumberMessage() +
                "Start method " +
                joinPoint.getSignature().getDeclaringTypeName() +
                "." +
                joinPoint.getSignature().getName() +
                "(" +
                createArgumentUsedInMethodMessage(joinPoint) +
                ")";
    }
    
    private String createEndingOfMethodMessage(ProceedingJoinPoint joinPoint, long invocationTimeInMillis) {
        return createPIDNumberMessage() +
                "Finish method " +
                joinPoint.getSignature().getDeclaringTypeName() +
                "." +
                joinPoint.getSignature().getName() +
                "(" + createArgumentUsedInMethodMessage(joinPoint) + ")" +
                "; execution time: " +
                invocationTimeInMillis +
                " ms;";
    }
    
    private String createPIDNumberMessage() {
        return "PID=[" + getPIDNumber() + "] ";
    }
    
    private long getPIDNumber() {
        return ProcessHandle.current().pid();
    }
    
    private String createArgumentUsedInMethodMessage(ProceedingJoinPoint joinPoint) {
        StringBuilder arguments = new StringBuilder();

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            arguments.append(arg).append(",");
        }
        if (args.length > 0) {
            arguments.deleteCharAt(arguments.length() - 1);
        }
        
        return arguments.toString();
    }
}
