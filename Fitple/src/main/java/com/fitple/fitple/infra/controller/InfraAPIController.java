package com.fitple.fitple.infra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InfraAPIController {

    @Value("${vworld.api.key}")
    private String apiKey;

    @Value("${vworld.api.domain}")
    private String apiDomain;

    private final RestTemplate restTemplate = new RestTemplate();

    // 시/도 목록 가져오기 API
    @GetMapping("/sido")
    public ResponseEntity<String> getSidoList() {
        String url = "https://api.vworld.kr/req/data?service=data" +
                "&request=GetFeature" +
                "&data=LT_C_ADSIDO_INFO" +
                "&key=" + apiKey +
                "&domain=" + apiDomain +
                "&type=json" +
                "&numOfRows=30" +
                "&size=30" +
                "&geomFilter=BOX(115,33,135,43)";

        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }

    // 시/군/구 목록 가져오기 API
    @GetMapping("/sigungu")
    public ResponseEntity<String> getSigunguList(@RequestParam String sidoCd) {
        String url = "https://api.vworld.kr/req/data?service=data" +
                "&request=GetFeature" +
                "&data=LT_C_ADSIGG_INFO" +
                "&key=" + apiKey +
                "&domain=" + apiDomain +
                "&type=json" +
                "&numOfRows=30" +
                "&size=30" +
                "&attrFilter=sig_cd:LIKE:" + sidoCd + "%";

        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }

    // 읍/면/동 조회 API
    @GetMapping("/emdList")
    public ResponseEntity<String> getEmdList(@RequestParam String sigunguCd) {
        String url = "https://api.vworld.kr/req/data?service=data" +
                "&request=GetFeature" +
                "&data=LT_C_ADEMD_INFO" +
                "&key=" + apiKey +
                "&domain=" + apiDomain +
                "&type=json" +
                "&numOfRows=30" +
                "&size=30" +
                "&attrFilter=emd_cd:LIKE:" + sigunguCd + "%";

        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }

    // 시/군/구 경계선 API
    @GetMapping("/polygon")
    public ResponseEntity<String> getPolygonData(@RequestParam String sigCd) {
        String url = "https://api.vworld.kr/req/data?service=data" +
                "&request=GetFeature" +
                "&data=LT_C_ADSIGG_INFO" +
                "&key=" + apiKey +
                "&domain=" + apiDomain +
                "&type=json" +
                "&attrFilter=sig_cd:=:" + sigCd;

        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}
