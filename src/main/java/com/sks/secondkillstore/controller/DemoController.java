package com.sks.secondkillstore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author HQD
 * @Date 2024/4/3 22:35
 * @Version 1.0
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","xxx");
        return "hello";
    }
}
