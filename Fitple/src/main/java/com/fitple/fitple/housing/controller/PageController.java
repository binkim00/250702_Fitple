package com.fitple.fitple.housing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // application.properties (or yml)에 정의한 kakao.js.key 값을 주입
    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    // /housing URL 요청 시 housing.html 렌더링 + kakaoKey 주입
    @GetMapping("/housing")
    public String housing(Model model) {
        model.addAttribute("kakaoKey", kakaoJsKey);
        return "housing"; // templates/housing.html (Thymeleaf) 기준
    }
}
