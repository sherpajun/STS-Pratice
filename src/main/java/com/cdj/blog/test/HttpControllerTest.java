package com.cdj.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//@Controller
//사용자가 요청-> 응답을 해주는 컨트롤러(HTML)를 응답한다.

//사용자가 요청-> 응답을 해주는 컨트롤러(DATA)를 응답한다.
@RestController
public class HttpControllerTest {

	//인터넷 요청은 무조건 get요청 밖에 없다.
	//http://localhost:7080/http/~~~/
//	public String getTest(@RequestParam int id,@RequestParam String username){
//	System.out.println(id);
//	System.out.println(username);
//	return "get 요청 확인:"+id+","+username;
//	이렇게 리퀘스트 파람으로 일일이 받아도 되며 다른 방법으론 아래와 같다.
//}
	@GetMapping("/http/get")
	public String getTest(Member m){
		System.out.println(m.getId());
		System.out.println(m.getUsername());
		System.out.println(m.getPassword());
		System.out.println(m.getEmail());
		return "get 요청 확인:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
		
	}
//	@PostMapping("/http/post")
//	public String postTest(Member m){
//		return "post 요청 확인:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
//	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m){//Message Converter가 활동
		return "post 요청 확인:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m){
		return "put 요청 확인:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m){
		return "삭제성공하였습니다.";
	}
}
