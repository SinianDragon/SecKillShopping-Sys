package com.sks.secondkillstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sks.secondkillstore.entity.SeckillOrder;
import com.sks.secondkillstore.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    /**
     * 功能描述：获取秒杀结果
     * @param user
     * @param goodsId
     * @return
     */
    Long getResult(User user, Long goodsId);
}
