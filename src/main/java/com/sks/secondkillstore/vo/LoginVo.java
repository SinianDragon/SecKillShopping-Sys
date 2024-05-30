package com.sks.secondkillstore.vo;

import com.sks.secondkillstore.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author HQD
 * @Date 2024/4/7 22:00
 * @Version 1.0
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;
}
