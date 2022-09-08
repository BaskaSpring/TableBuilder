package ru.mfn.TableBuilder.model.validator;

import lombok.SneakyThrows;
import ru.mfn.TableBuilder.payload.exception.InvalidNameException;
import ru.mfn.TableBuilder.payload.exception.InvalidNumberIdException;
import ru.mfn.TableBuilder.model.annotation.ValidName;
import ru.mfn.TableBuilder.util.MatchingUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    @SneakyThrows
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name==null){
            throw new InvalidNumberIdException("Error: invalid name!");
        }
        if(!name.matches(MatchingUtil.FIELD_NAME_REGEX)) {
            throw new InvalidNameException(context.getDefaultConstraintMessageTemplate() + ": " + name);
        }
        return true;
    }


}
