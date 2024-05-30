package com.sks.secondkillstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sks.secondkillstore.entity.Goods;
import com.sks.secondkillstore.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 获取商品列表
     * @return 商品列表
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 获取商品详情
     * @param goodsId 商品ID
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
