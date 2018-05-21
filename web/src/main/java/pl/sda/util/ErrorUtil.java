package pl.sda.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ErrorUtil {

    public static <T> List<String> getErrors(T dto) {
        List<String> errors = new ArrayList<>();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(dto);
        if (!constraintViolations.isEmpty()) {
            constraintViolations.forEach(constraint ->
                    errors.add(constraint.getPropertyPath() + " " + constraint.getMessage()));
        }
        return errors;
    }
}
