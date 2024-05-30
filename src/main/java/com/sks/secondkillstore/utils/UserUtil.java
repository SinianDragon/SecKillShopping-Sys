package com.sks.secondkillstore.utils;

import com.sks.secondkillstore.entity.User;
import com.sks.secondkillstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成用户工具类
 *
 * @Author HQD
 * @Date 2024/4/23 21:21
 * @Version 1.0
 */
@Component
public class UserUtil {

    @Autowired
    private IUserService userService;

    public void CreateUser() throws IOException {
        List<User> list = new ArrayList<>();
//        生成用户
        for (int i = 0; i < 100; i++) {
            User tUser = new User();
            tUser.setId(1233L + i);
            tUser.setNickname("user" + i);
            tUser.setSalt("1a2b3c");
            tUser.setPassword("6e0a7fe692684372437c9e508508990d");
            list.add(tUser);
        }
        userService.saveBatch(list);
    }


}
