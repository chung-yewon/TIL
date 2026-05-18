package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // REST API를 만드는 컨트롤러임을 선언
public class HelloController {

    @GetMapping("/hello") // 웹 브라우저에서 /hello로 접속하면 실행
    public String hello() {
        return "Hello, Likelion!";
    }
}