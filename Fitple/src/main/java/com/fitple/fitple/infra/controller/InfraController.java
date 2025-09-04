package com.fitple.fitple.infra.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfraController {

    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    @GetMapping("/infra")
    public String infra(Model model) {
        model.addAttribute("kakaoKey", kakaoJsKey);
        return "infra"; // templates/infra.html
    }
}
