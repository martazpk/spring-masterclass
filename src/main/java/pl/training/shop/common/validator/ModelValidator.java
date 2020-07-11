package pl.training.shop.common.validator;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@Aspect
@RequiredArgsConstructor
public class ModelValidator {

    private final ValidatorService validatorService;

    @Before("execution(* *(@Validate (*)))")
    public void validate(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        IntStream.range(0, args.length)
                .forEach(index -> validate(signature, args[index],index));

    }

    private void validate(MethodSignature signature, Object arg, int index) {
        Annotation[] annotations = getAnnotations(signature, index);
        Optional<Validate> validateAnnotation = getValidateAnnotation(annotations);
        validateAnnotation.ifPresent(validate -> validatorService.validate(arg, validate.exception()));

    }

    private Annotation[] getAnnotations(MethodSignature signature, int index) {
        return signature.getMethod().getParameterAnnotations()[index];
    }

    private Optional<Validate> getValidateAnnotation(Annotation[] annotations){
        return Arrays.stream(annotations)
                .filter(annotation -> annotation instanceof Validate)
                .map(annotation -> (Validate) annotation)
                .findFirst();
    }
}
