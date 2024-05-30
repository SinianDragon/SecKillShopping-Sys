package com.sks.secondkillstore.vo;

import com.sks.secondkillstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HQD
 * @Date 2024/4/22 18:10
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {
    private User user;
    private GoodsVo goodsVo;
    private int secKillStatus;
    private int remainSeconds;
}
