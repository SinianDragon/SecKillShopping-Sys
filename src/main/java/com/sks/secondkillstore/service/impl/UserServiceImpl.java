package com.sks.secondkillstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.exception.GlobeException;
import com.sks.secondkillstore.mapper.UserMapper;
import com.sks.secondkillstore.service.IUserService;
import com.sks.secondkillstore.utils.CookieUtil;
import com.sks.secondkillstore.utils.MD5Util;
import com.sks.secondkillstore.utils.UUIDUtil;
import com.sks.secondkillstore.utils.ValidatorUtil;
import com.sks.secondkillstore.vo.LoginVo;
import com.sks.secondkillstore.vo.RespBean;
import com.sks.secondkillstore.vo.RespBeanEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HQD
 * @since 2024-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录校验
     *
     * @param loginVo 登录消息
     * @return 传递消息
     */
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //被自定义注解代替
//        if (mobile.equals("") || password.equals("")) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        //手机号码校验
//        if (!ValidatorUtil.is_mobile(mobile)) {
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if (user == null) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobeException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if (!MD5Util.fromPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobeException(RespBeanEnum.LOGIN_ERROR);
        }
        String uuid = UUIDUtil.uuid();
//        request.getSession().setAttribute(uuid, user);
        //将用户信息存入value中
        redisTemplate.opsForValue().set("user:" + uuid, user);

        CookieUtil.setCookie(request, response, "userTicket", uuid);
        return RespBean.success(RespBeanEnum.SUCCESS);
    }

    /**
     * 根据cookie获取用户
     *
     * @param uuid cookie生产的通过码
     * @return 返回查询到的用户
     */
    @Override
    public User getUserByCookie(String uuid, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(uuid)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + uuid);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", uuid);
        }
        return user;
    }

    /**
     * 更新密码
     *
     * @param userTicket
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null) {
            throw new GlobeException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToDBPass(password, user.getSalt()));
        int result = userMapper.updateById(user);
        if (result == 1) {
            //删除redis中的记录
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }else {
            return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
        }
    }
}
