package pdv.com.br.pdv.validation.contraintvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pdv.com.br.pdv.validation.NotEmptyList;

import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


}
