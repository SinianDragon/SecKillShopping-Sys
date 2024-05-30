package com.sks.secondkillstore.utils;

import com.sks.secondkillstore.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HQD
 * @Date 2024/4/7 22:16
 * @Version 1.0
 * 手机号码校验类
 */
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean is_mobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }
}
