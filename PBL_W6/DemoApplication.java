package com.example.demo;

import com.example.demo.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너(ApplicationContext)를 가져옵니다.
		ApplicationContext ac = SpringApplication.run(DemoApplication.class, args);

		// 컨테이너에서 "memberService"라는 Bean을 꺼내옵니다.
		MemberService memberService = ac.getBean(MemberService.class);

		// 객체가 잘 꺼내졌는지 확인 (null이 아니면 성공!)
		System.out.println("memberService = " + memberService);
	}
}