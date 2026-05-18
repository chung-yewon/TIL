package com.example.demo.config;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration // 이 클래스가 스프링의 "설정 파일"임을 선언합니다.
public class AppConfig {

    @Bean // 스프링 컨테이너에 "memberRepository"라는 이름으로 객체를 등록합니다.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean // 스프링 컨테이너에 "memberService"라는 이름으로 객체를 등록합니다.
    public MemberService memberService() {
        // 위에서 등록한 memberRepository 객체를 넣어주며(주입하며) 생성합니다.
        return new MemberService(memberRepository());
    }
}