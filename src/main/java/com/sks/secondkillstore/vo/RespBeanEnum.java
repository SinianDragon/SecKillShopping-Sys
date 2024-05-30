package com.sks.secondkillstore.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author HQD
 * @Date 2024/4/6 21:26
 * @Version 1.0
 */
//公共返回对象枚举
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),
    //登录
    LOGIN_ERROR(500210, "用户名或者密码错误"),
    MOBILE_ERROR(500211, "手机号为空或格式错误"),
    BIND_ERROR(500212, "参数校验异常"),
    OTHER_ERROR(500213, "其它异常情况"),
    MOBILE_NOT_EXIST(500214, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500215, "更新密码失败"),
    SESSION_ERROR(500215, "用户不存在"),
    //秒杀模块5005xx
    EMPTY_STOCK(500500, "库存不足"),
    REPEATE_ERROR(500501, "每人限购一件商品"),
    //订单模块5003xx
    ORDER_NOT_EXIST(500300, "订单信息不存在");
    private final Integer code;
    private final String message;
}
