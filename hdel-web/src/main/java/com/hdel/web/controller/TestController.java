package com.hdel.web.controller;

import com.hdel.web.service.HoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("/api/v1/comm")
public class TestController {

    private final HoInfoService hoInfoService;

    @GetMapping("/test")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        int i = 1;
        i ++;
        System.out.println(request.getHeader("user-agent"));

        return String.valueOf(i);
    }


    @GetMapping("/test/t1")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        int i = 1;
        i ++;
        System.out.println(request.getHeader("user-agent"));

        return String.valueOf(i);
    }

    //mybatis test
    @Autowired
    public TestController(HoInfoService hoInfoService){
        this.hoInfoService = hoInfoService;
    }

    /*@GetMapping(value = "/testMybatis")
    public HoInfo test() throws Exception {
        return hoInfoService.test();
    }*/

    //@GetMapping("/login/oauth2/code/google")
    public String googleLogin() {
        int i = 1;
        i ++;

        return String.valueOf(i);
    }
}


