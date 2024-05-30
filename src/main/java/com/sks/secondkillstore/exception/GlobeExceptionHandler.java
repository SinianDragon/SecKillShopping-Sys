package com.sks.secondkillstore.exception;

import com.sks.secondkillstore.vo.RespBean;
import com.sks.secondkillstore.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Author HQD
 * @Date 2024/4/8 9:04
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobeExceptionHandler {
    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e) {
        if (e instanceof GlobeException) {
            GlobeException ex = (GlobeException) e;
            return RespBean.error(ex.getRespBeanEnum());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            RespBean error = RespBean.error(RespBeanEnum.BIND_ERROR);
            error.setMessage("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return error;
        }
        RespBean error = RespBean.error(RespBeanEnum.OTHER_ERROR);
        error.setMessage("其它异常：" + e.getMessage() + " " + e.getCause());
        return error;
    }

}
