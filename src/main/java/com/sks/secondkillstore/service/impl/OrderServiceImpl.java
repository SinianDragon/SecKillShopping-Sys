package com.sks.secondkillstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sks.secondkillstore.entity.Order;
import com.sks.secondkillstore.entity.SeckillGoods;
import com.sks.secondkillstore.entity.SeckillOrder;
import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.exception.GlobeException;
import com.sks.secondkillstore.mapper.OrderMapper;
import com.sks.secondkillstore.service.IGoodsService;
import com.sks.secondkillstore.service.IOrderService;
import com.sks.secondkillstore.service.ISeckillGoodsService;
import com.sks.secondkillstore.service.ISeckillOrderService;
import com.sks.secondkillstore.vo.GoodsVo;
import com.sks.secondkillstore.vo.OrderDetailVo;
import com.sks.secondkillstore.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public Order seckill(User user, GoodsVo goods) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>()
                .eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        //解决了超卖问题，但是数据库的表有问题
//        boolean seckillGoodsResult = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>().set("stock_count",
//                seckillGoods.getStockCount()).eq("id", seckillGoods.getId()).gt("stock_count", 0));
        boolean seckillGoodsResult = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>()
                .setSql("stock_count = stock_count - 1")
                .eq("goods_id", goods.getId())
                .gt("stock_count", 0));
        if (seckillGoods.getStockCount() < 1) {
            valueOperations.set("isStockEmpty" + goods.getId(), "0");
            return null;
        }
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goods.getId(), seckillOrder);
        return order;
    }

    @Override
    public OrderDetailVo detail(Long orderId) {
        if (orderId == null) {
            throw new GlobeException(RespBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrder(order);
        orderDetailVo.setGoodsVo(goodsVo);
        return orderDetailVo;
    }
}
