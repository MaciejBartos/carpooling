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

    @Around("execution(* pl.lodz.p.edu.carpooling.CMS..* (..)) || " +
            "execution(* pl.lodz.p.edu.carpooling.persistence.dao..*(..)) || " +
            "execution(* pl.lodz.p.edu.carpooling.persistence.repository..*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object retVal;
        try {
            StringBuilder startMessage = new StringBuilder();
            String pidNumber = "PID=[" + ProcessHandle.current().pid() + "] ";
            StringBuilder endMessage = new StringBuilder();
            StringBuilder arguments = new StringBuilder();

            startMessage.append(pidNumber);
            startMessage.append("Start method ");
            startMessage.append(joinPoint.getSignature().getDeclaringTypeName());
            startMessage.append(".");
            startMessage.append(joinPoint.getSignature().getName());
            startMessage.append("(");

            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                arguments.append(arg).append(",");
            }
            if (args.length > 0) {
                arguments.deleteCharAt(arguments.length() - 1);
            }

            startMessage.append(arguments);
            startMessage.append(")");

            log.info(startMessage.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            retVal = joinPoint.proceed();

            stopWatch.stop();

            endMessage.append(pidNumber);
            endMessage.append("Finish method ");
            endMessage.append(joinPoint.getSignature().getDeclaringTypeName());
            endMessage.append(".");
            endMessage.append(joinPoint.getSignature().getName());
            endMessage.append("(").append(arguments).append(")");
            endMessage.append("; execution time: ");
            endMessage.append(stopWatch.getTotalTimeMillis());
            endMessage.append(" ms;");

            log.info(endMessage.toString());
        } catch (ObjectOptimisticLockingFailureException ex) {
            log.error("ObjectOptimisticLockingFailureException occurred for: " + ex.getPersistentClassName() + ", id=[" + ex.getIdentifier() + "]");
            throw BaseAppException.createOptimisticLockException();
        }
        return retVal;
    }
}
