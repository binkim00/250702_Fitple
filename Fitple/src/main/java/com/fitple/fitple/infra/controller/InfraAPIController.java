package com.fitple.fitple.infra.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
    public ResponseEntity<?> getSidoList() {
        try {
            String url = "https://api.vworld.kr/req/data?service=data" +
                    "&request=GetFeature" +
                    "&data=LT_C_ADSIDO_INFO" +
                    "&key=" + apiKey +
                    "&domain=" + apiDomain +
                    "&type=json" +
                    "&numOfRows=30" +
                    "&size=30" +
                    "&geomFilter=BOX(115,33,135,43)";

            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<JsonNode>() {}
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Vworld API 호출 실패: " + e.getMessage());
        }
    }

    // 시/군/구 목록 가져오기 API
    @GetMapping("/sigungu")
    public ResponseEntity<?> getSigunguList(@RequestParam String sidoCd) {
        try {
            String url = "https://api.vworld.kr/req/data?service=data" +
                    "&request=GetFeature" +
                    "&data=LT_C_ADSIGG_INFO" +
                    "&key=" + apiKey +
                    "&domain=" + apiDomain +
                    "&type=json" +
                    "&numOfRows=30" +
                    "&size=30" +
                    "&attrFilter=sig_cd:LIKE:" + sidoCd + "%";

            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<JsonNode>() {}
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Vworld API 호출 실패: " + e.getMessage());
        }
    }

    // 읍/면/동 조회 API
    @GetMapping("/emdList")
    public ResponseEntity<?> getEmdList(@RequestParam String sigunguCd) {
        try {
            String url = "https://api.vworld.kr/req/data?service=data" +
                    "&request=GetFeature" +
                    "&data=LT_C_ADEMD_INFO" +
                    "&key=" + apiKey +
                    "&domain=" + apiDomain +
                    "&type=json" +
                    "&numOfRows=30" +
                    "&size=30" +
                    "&attrFilter=emd_cd:LIKE:" + sigunguCd + "%";

            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<JsonNode>() {}
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Vworld API 호출 실패: " + e.getMessage());
        }
    }

    // 시/군/구 경계선 API
    @GetMapping("/polygon")
    public ResponseEntity<?> getPolygonData(@RequestParam String sigCd) {
        try {
            String url = "https://api.vworld.kr/req/data?service=data" +
                    "&request=GetFeature" +
                    "&data=LT_C_ADSIGG_INFO" +
                    "&key=" + apiKey +
                    "&domain=" + apiDomain +
                    "&type=json" +
                    "&attrFilter=sig_cd:=:" + sigCd;

            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<JsonNode>() {}
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Vworld API 호출 실패: " + e.getMessage());
        }
    }
}
