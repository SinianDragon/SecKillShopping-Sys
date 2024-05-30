package com.sks.secondkillstore.exception;

import com.sks.secondkillstore.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HQD
 * @Date 2024/4/8 8:44
 * @Version 1.0
 * 全局异常
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobeException extends RuntimeException{
    private RespBeanEnum respBeanEnum;
}
