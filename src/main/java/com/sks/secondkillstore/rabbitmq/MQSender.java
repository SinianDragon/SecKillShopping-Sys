package com.sks.secondkillstore.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @Author HQD
 * @Date 2024/4/25 16:24
 * @Version 1.0
 */
@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送秒杀信息
     * @param message   秒杀信息
     */
    public void sendSeckillMessage(String message) {
        log.info("发送消息：" + message);
        rabbitTemplate.convertAndSend("seckillExchange", "seckill.message", message);
    }
    //测试情况
//    public void send(Object msg) {
//        log.info("发送消息：" + msg);
//        rabbitTemplate.convertAndSend("fanoutExchange", "", msg);
//    }
//
//    public void dsend01(Object msg) {
//        log.info("发送消息：" + msg);
//        rabbitTemplate.convertAndSend("directExchange", "queue.red", msg);
//    }
//
//    public void dsend02(Object msg) {
//        log.info("发送消息：" + msg);
//        rabbitTemplate.convertAndSend("directExchange", "queue.green", msg);
//    }
}
