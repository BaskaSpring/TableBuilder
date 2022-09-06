package ru.mfn.TableBuilder.model.annotation;


import ru.mfn.TableBuilder.model.validator.ValidRoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidRoleValidator.class)
public @interface ValidRoleName {
    String message() default "Invalid field name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
