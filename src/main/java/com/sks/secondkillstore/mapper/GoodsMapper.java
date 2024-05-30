package com.sks.secondkillstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sks.secondkillstore.entity.Goods;
import com.sks.secondkillstore.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取商品列表
     * @return 商品列表
     */
    @Select("SELECT g.id,\n" +
            "               g.goods_name,\n" +
            "               g.goods_title,\n" +
            "               g.goods_img,\n" +
            "               g.goods_price,\n" +
            "               g.goods_stock,\n" +
            "               sg.seckill_price,\n" +
            "               sg.stock_count,\n" +
            "               sg.start_date,\n" +
            "               sg.end_date\n" +
            "        FROM t_goods g\n" +
            "                 LEFT JOIN t_seckill_goods sg on g.id = sg.goods_id")
    List<GoodsVo> findGoodsVo();

    @Select("SELECT g.id,\n" +
            "               g.goods_name,\n" +
            "               g.goods_title,\n" +
            "               g.goods_img,\n" +
            "               g.goods_price,\n" +
            "               g.goods_stock,\n" +
            "               sg.seckill_price,\n" +
            "               sg.stock_count,\n" +
            "               sg.start_date,\n" +
            "               sg.end_date\n" +
            "        FROM t_goods g\n" +
            "                 LEFT JOIN t_seckill_goods sg on g.id = sg.goods_id\n" +
            "        WHERE\n" +
            "            g.id=#{goodsId}")
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
