package com.sks.secondkillstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HQD
 * @Date 2024/4/25 20:47
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillMessage {
    private User user;
    private Long goodsId;
}
