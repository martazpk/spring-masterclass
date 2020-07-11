package pl.training.shop.common.validator;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RequiredArgsConstructor
public class ValidatorService {
    private final Validator validator;

    public <O, E extends RuntimeException> void validate(O object, Class<E> exceptionType) throws E{
        Set<ConstraintViolation<O>> violations = validator.validate(object);
        if(!violations.isEmpty()){
            Constructor<E> exception = null;
            try {
                exception = exceptionType.getDeclaredConstructor();
                throw exception.newInstance();
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new IllegalArgumentException();
            }
        }
    }

}
