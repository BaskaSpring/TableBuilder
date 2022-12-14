package ru.mfn.TableBuilder.model.annotation;


import ru.mfn.TableBuilder.model.validator.ValidNumberIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidNumberIdValidator.class)
public @interface ValidNumberId {
    String message() default "Invalid id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
