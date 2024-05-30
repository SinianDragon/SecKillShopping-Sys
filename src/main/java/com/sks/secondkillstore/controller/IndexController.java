package com.sks.secondkillstore.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class IndexController {

    @SneakyThrows
    @GetMapping("/")
    public void index(HttpServletRequest req, HttpServletResponse res){
        log.info("访问到/");
        res.sendRedirect("/login/toLogin");
    }

}
