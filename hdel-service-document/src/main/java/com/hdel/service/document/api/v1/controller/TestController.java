package com.hdel.service.document.api.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/getTestMessage")
    public String getTestMessage(Model model) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a1", "ID");

        model.addAttribute("test", map);
        //return "test/hello.html";
        return "page/main/index.html";
    }
}
