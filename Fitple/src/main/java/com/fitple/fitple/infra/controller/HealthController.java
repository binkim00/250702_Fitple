package com.fitple.fitple.infra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    // Elastic Beanstalk가 "/" 로 헬스체크 요청을 보낼 때 항상 200 OK 반환
    @GetMapping("/")
    public String rootHealth() {
        return "ok";
    }

    // 별도의 헬스체크 엔드포인트도 제공 (선택)
    @GetMapping("/healthz")
    public String healthz() {
        return "ok";
    }
}
