package com.hdel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestThymeleaf {
    @RequestMapping(value = "/testThymeleaf1",method = RequestMethod.GET)
    public String testThymeleaf(Model model){
        model.addAttribute("hello","타임리프 테스트!");
        return "/testThymeleaf";
    }

    @RequestMapping(value = "/testTemplete",method = RequestMethod.GET)
    public String testTemplete(Model model){
        //model.addAttribute("hello","타임리프 테스트!");
        //return "/sample/plain"; // linux 에서 인식 못함 아래처럼 ..
        return "sample/plain";
    }
}


