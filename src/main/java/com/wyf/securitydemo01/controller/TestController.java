package com.wyf.securitydemo01.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wyf
 * @Date: 2021/4/21 11:29
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello xxxx";
    }

    @GetMapping("/index")
    public String index(){
        return "hello index";
    }

    @GetMapping("/update")
//    @Secured({"ROLE_manager","ROLE_sale"})
    @PreAuthorize("hasAnyAuthority('ROLE_manager')")
    public String update(){
        return "hello update";
    }

    //退出
    @GetMapping("/logout")
    public String logout(){
        return "退出成功！";
    }

}
