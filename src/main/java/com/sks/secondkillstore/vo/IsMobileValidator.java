package com.sks.secondkillstore.vo;

import com.sks.secondkillstore.utils.ValidatorUtil;
import com.sks.secondkillstore.validator.IsMobile;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @Author HQD
 * @Date 2024/4/7 22:54
 * @Version 1.0
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.is_mobile(value);
        }else{
            if(StringUtils.isEmpty(value)){
                return true;
            }else {
                return ValidatorUtil.is_mobile(value);
            }
        }
    }
}
