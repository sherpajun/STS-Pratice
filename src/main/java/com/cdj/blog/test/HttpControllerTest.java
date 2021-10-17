package com.cdj.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
//@Controller
//사용자가 요청-> 응답을 해주는 컨트롤러(HTML)를 응답한다.

//사용자가 요청-> 응답을 해주는 컨트롤러(DATA)를 응답한다.
@RestController
public class HttpControllerTest {

	
	//http://localhost:7080/http/~~~/
	@GetMapping("/http/get")
	public String getTest(){
		return "get 요청 확인";
	}
	@PostMapping("/http/post")
	public String postTest(){
		return "post 요청 확인";
	}
	@PostMapping("/http/put")
	public String putTest(){
		return "put 요청 확인";
	}
	@DeleteMapping("/http/delete")
	public String deleteTest(){
		return "delete 요청 확인";
	}
}
