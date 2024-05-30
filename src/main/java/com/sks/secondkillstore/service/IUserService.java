package com.sks.secondkillstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.vo.LoginVo;
import com.sks.secondkillstore.vo.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author HQD
 * @since 2024-04-06
 */
public interface IUserService extends IService<User> {

    /**
     * 登录校验
     *
     * @param loginVo 登录消息
     * @return 传递消息
     */
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据cookie获取用户
     *
     * @param uuid cookie生产的通过码
     * @return 返回查询到的用户
     */
    User getUserByCookie(String uuid, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新用户密码
     *
     * @param userTicket
     * @param password
     * @return
     */
    RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response);
}
