package com.sks.secondkillstore.controller;


import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.rabbitmq.MQSender;
import com.sks.secondkillstore.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HQD
 * @since 2024-04-06
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MQSender mqSender;

    /**
     * 用户信息测试
     *
     * @param user 登录用户
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }

//    /**
//     * 发送消息测试
//     */
//    @RequestMapping("/mq")
//    @ResponseBody
//    public void mq() {
//        mqSender.send("Hello");
//    }
//
//    /**
//     * 不同模式发送消息
//     */
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public void mq01() {
//        mqSender.send("Hello fanout");
//    }
//
//    @RequestMapping("/mq/direct")
//    @ResponseBody
//    public void mq02() {
//        mqSender.dsend01("Hello direct red");
//    }
}

