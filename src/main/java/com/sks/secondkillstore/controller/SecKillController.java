package com.sks.secondkillstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sks.secondkillstore.entity.Order;
import com.sks.secondkillstore.entity.SeckillMessage;
import com.sks.secondkillstore.entity.SeckillOrder;
import com.sks.secondkillstore.entity.User;

import com.sks.secondkillstore.rabbitmq.MQSender;
import com.sks.secondkillstore.service.IGoodsService;
import com.sks.secondkillstore.service.IOrderService;
import com.sks.secondkillstore.service.ISeckillOrderService;
import com.sks.secondkillstore.utils.JsonUtil;
import com.sks.secondkillstore.vo.GoodsVo;
import com.sks.secondkillstore.vo.RespBean;
import com.sks.secondkillstore.vo.RespBeanEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Author HQD
 * @Date 2024/4/21 15:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController implements InitializingBean {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MQSender mqSender;
    //Long对应不同的商品ID，Boolean代表是否卖完
    private Map<Long, Boolean> EmptyStockMap = new HashMap<>();

    @Autowired
    private RedisScript<Long> redisScript;

    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill(User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        //秒杀优化至百万
        /*
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        if (goods.getStockCount() < 1) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //判断是否重复抢购
//        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
//                .eq("user_id", user.getId()).eq("goods_id", goodsId));
        SeckillOrder seckillOrder =
                (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goods.getId());
        if (seckillOrder != null) {
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
        Order order = orderService.seckill(user, goods);
        return RespBean.success(order);
        */
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) valueOperations.get("order:" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
        //通过内存标记减少redis访问
        if (EmptyStockMap.get(goodsId)) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //原子性操作，预减库存
//        Long stock = valueOperations.decrement("seckillGoods:" + goodsId);
        //使用redis分布式锁优化
        Long stock = (Long) redisTemplate.execute(redisScript, Collections.singletonList("seckillGoods:" + goodsId)
                , Collections.EMPTY_LIST);
        if (stock < 0) {
            EmptyStockMap.put(goodsId, true);
            valueOperations.increment("seckillGoods:" + goodsId);
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        SeckillMessage seckillMessage = new SeckillMessage(user, goodsId);
        mqSender.sendSeckillMessage(JsonUtil.object2JsonStr(seckillMessage));
        return RespBean.success(0);
    }

    /**
     * 获取秒杀结果
     *
     * @param user 用户
     * @return orderId 成功; -1 秒杀失败，0 排队中
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public RespBean getResult(User user,Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        Long orderId = seckillOrderService.getResult(user, goodsId);
        return RespBean.success(orderId);
    }

    @RequestMapping("/doSeckill2")
    public String doSeckill2(Model model, User user, Long goodsId) {
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        if (goods.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
                .eq("user_id", user.getId()).eq("goods_id", goodsId));
        if (seckillOrder != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        Order order = orderService.seckill(user, goods);
        model.addAttribute("order", order);
        model.addAttribute("goods", goods);
        return "orderDetail";
    }

    /**
     * 系统初始化操作，将商品库存提前加载到redis中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> list = goodsService.findGoodsVo();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(goodsVo -> {
            redisTemplate.opsForValue().set("seckillGoods:" + goodsVo.getId(), goodsVo.getStockCount());
            EmptyStockMap.put(goodsVo.getId(), false);
        });
    }
}
