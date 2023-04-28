package com.hdel.web.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index(Model model){
        //model.addAttribute("hello","타임리프 테스트!");
        return "index";
    }
}
