package com.sks.secondkillstore.validator;

import com.sks.secondkillstore.vo.IsMobileValidator;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @Author HQD
 * @Date 2024/4/7 22:50
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {
                IsMobileValidator.class
        }
)
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机号码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
