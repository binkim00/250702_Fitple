package com.fitple.fitple.housing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HousingViewController {

    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    @GetMapping("/housing")
    public String housingMapPage(Model model) {
        // 카카오 JS 키를 뷰에 전달
        model.addAttribute("kakaoKey", kakaoJsKey);
        // templates/housing/housing_map_test.html 렌더링
        return "housing/housing_map_test";
    }
}
