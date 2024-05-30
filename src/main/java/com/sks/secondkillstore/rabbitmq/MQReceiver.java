package com.sks.secondkillstore.rabbitmq;

import com.sks.secondkillstore.entity.SeckillMessage;
import com.sks.secondkillstore.entity.SeckillOrder;
import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.service.IGoodsService;
import com.sks.secondkillstore.service.IOrderService;
import com.sks.secondkillstore.utils.JsonUtil;
import com.sks.secondkillstore.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author HQD
 * @Date 2024/4/25 16:27
 * @Version 1.0
 */
@Service
@Slf4j
public class MQReceiver {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IOrderService orderService;

    /**
     * 下单操作
     * @param message 下单情况
     */
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message) {
        log.info("接收消息：" + message);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        if(goodsVo.getStockCount() < 1){
            return;
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder =
                (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            return;
        }
        orderService.seckill(user,goodsVo);
    }

//    @RabbitListener(queues = "queue")
//    public void receive(Object msg) {
//        log.info("接收消息为：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout01")
//    public void receive01(Object msg) {
//        log.info("QUEUE01接收消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout02")
//    public void receive02(Object msg) {
//        log.info("QUEUE02接收消息：" + msg);
//    }
//
//    @RabbitListener(queues = "direct_queue01")
//    public void dreceive01(Object msg) {
//        log.info("direct_queue01接收消息：" + msg);
//    }
//
//    @RabbitListener(queues = "direct_queue02")
//    public void dreceive02(Object msg) {
//        log.info("direct_queue02接收消息：" + msg);
//    }
}
