//package com.cdj.blog.test;
//
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cdj.blog.repository.UserRepository;
//
//@RestController
//public class DummyControllerTest {
//
//	@Autowired//의존선 주입
//	private UserRepository userRepository;
//	
//	@PostMapping("/dummy/join")
//	public String join (User user) {//x-www-form url encoded key&value
//		System.out.println("username:" +user.getUsername());
//		System.out.println("password:" +user.getPassword());
//		System.out.println("email:" +user.getUserDatabase());
//		
//		userRepository.save(user);
//		return "access success";
//		
//		
//	}
//	 
//	
//	
//	
//	
//	
//}
