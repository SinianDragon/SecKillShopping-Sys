package com.sks.secondkillstore.vo;

import com.sks.secondkillstore.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HQD
 * @Date 2024/4/23 20:38
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {
    private Order order;

    private GoodsVo goodsVo;
}
