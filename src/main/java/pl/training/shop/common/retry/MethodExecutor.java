package pl.training.shop.common.retry;

import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MethodExecutor {
    @Setter
    private int attempts = 3;

    @Around("@annotation(Retry)")
    public Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
        int counter = 0;
        Throwable throwable;

         do {
            try {
                return joinPoint.proceed();
            } catch (Throwable t) {
                throwable = t;
                counter++;
            }
        }while (counter <= attempts);
        throw throwable;
    }
    
}
