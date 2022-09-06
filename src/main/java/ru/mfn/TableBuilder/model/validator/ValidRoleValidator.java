package ru.mfn.TableBuilder.model.validator;

import ru.mfn.TableBuilder.model.annotation.ValidRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidRoleValidator  implements ConstraintValidator<ValidRoleName, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
