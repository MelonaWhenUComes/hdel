package com.hdel.web.controller.test.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/test")
public class BoardController {

    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("test", "test");

        return "/test/board";
    }
}
