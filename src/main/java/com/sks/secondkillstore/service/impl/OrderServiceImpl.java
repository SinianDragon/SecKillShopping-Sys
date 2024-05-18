package com.sks.secondkillstore.service.impl;

import com.sks.secondkillstore.entity.Order;
import com.sks.secondkillstore.mapper.OrderMapper;
import com.sks.secondkillstore.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
