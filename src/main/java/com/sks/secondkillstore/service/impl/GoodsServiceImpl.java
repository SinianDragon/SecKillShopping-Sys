package com.sks.secondkillstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sks.secondkillstore.entity.Goods;
import com.sks.secondkillstore.mapper.GoodsMapper;
import com.sks.secondkillstore.service.IGoodsService;
import com.sks.secondkillstore.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 获取商品列表
     * @return 商品列表
     */
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
