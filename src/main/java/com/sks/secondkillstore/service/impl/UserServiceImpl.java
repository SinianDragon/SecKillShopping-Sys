package com.sks.seckilldemo.service.impl;

import com.sks.seckilldemo.entity.User;
import com.sks.seckilldemo.mapper.UserMapper;
import com.sks.seckilldemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HQD
 * @since 2024-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
