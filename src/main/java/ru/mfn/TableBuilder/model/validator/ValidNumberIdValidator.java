package ru.mfn.TableBuilder.model.validator;

import lombok.SneakyThrows;
import ru.mfn.TableBuilder.exception.InvalidNumberIdException;
import ru.mfn.TableBuilder.model.annotation.ValidNumberId;
import ru.mfn.TableBuilder.util.MatchingUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidNumberIdValidator implements ConstraintValidator<ValidNumberId, Long> {


    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if (id==null){
            throw new InvalidNumberIdException("Error: id is null");
        }
        if(!id.toString().matches(MatchingUtil.ID_REGEX)) {
            throw new InvalidNumberIdException(context.getDefaultConstraintMessageTemplate() + ": " + id);
        }

        return true;
    }
}
