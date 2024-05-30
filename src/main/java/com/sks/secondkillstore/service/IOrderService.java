package com.sks.secondkillstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sks.secondkillstore.entity.Order;
import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.vo.GoodsVo;
import com.sks.secondkillstore.vo.OrderDetailVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goods);

    /**
     * 订单详情
     * @return
     */
    OrderDetailVo detail(Long orderId);
}
