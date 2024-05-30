package com.sks.secondkillstore.controller;


import com.sks.secondkillstore.service.IUserService;
import com.sks.secondkillstore.vo.LoginVo;
import com.sks.secondkillstore.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * @Author HQD
 * @Date 2024/4/6 21:06
 * @Version 1.0
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private IUserService iUserService;

    /**
     * 登录页面
     *
     * @return 返回到登录页面的html
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "Login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    @Valid
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        return iUserService.doLogin(loginVo, request, response);
    }
}
