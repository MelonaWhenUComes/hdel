package com.hdel.web.controller;

import com.hdel.web.service.HoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comm")
public class TestController {

    private final HoInfoService hoInfoService;

    @GetMapping("/test")
    public String index() {
        int i = 1;
        i ++;

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
}
