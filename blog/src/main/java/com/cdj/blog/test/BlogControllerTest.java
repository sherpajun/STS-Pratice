package com.cdj.blog.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 스프링이 com.cdj.blog.test 패키지 이하를 스캔함.
					 // 특정 어노테이션이 붙어 있는 클래스 파일들을 new 해서 (ioc)스프링 컨테이너에 관리 해줍니다.
public class BlogControllerTest {
	
	@GetMapping("/cdj/hello")
	public String hello() {
		return "<h1>hello Test user</h1>";
	}
}
