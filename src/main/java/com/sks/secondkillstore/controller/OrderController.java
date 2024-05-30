package com.sks.secondkillstore.controller;


import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.service.IOrderService;
import com.sks.secondkillstore.vo.OrderDetailVo;
import com.sks.secondkillstore.vo.RespBean;
import com.sks.secondkillstore.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HQD
 * @since 2024-04-20
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 订单详情
     *
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public RespBean detail(User user, Long orderId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }
}

