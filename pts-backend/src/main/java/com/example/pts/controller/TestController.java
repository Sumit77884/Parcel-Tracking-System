package com.example.pts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/parcels/test")
    public Map<String,Object> testCreate(@RequestBody Map<String,Object> body) {
        Map<String,Object> res = new HashMap<>();
        res.put("trackingNumber", "PTS-TEST-0001");
        res.put("request", body);
        return res;
    }
}
