package com.cdj.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
 private  int id;
 private  String username;
 private  String password;
 private  String email;
//private 로 만드는 이유는 행위를 메소드로 제어하기 위해서 이다.
 
 
// public int getId() {
//	return id;
//}
//public Member(int id, String username, String password, String email) {
//	this.id = id;
//	this.username = username;
//	this.password = password;
//	this.email = email;
//}
//public void setId(int id) {
//	this.id = id;
//}
//public String getUsername() {
//	return username;
//}
//public void setUsername(String username) {
//	this.username = username;
//}
//public String getPassword() {
//	return password;
//}
//public void setPassword(String password) {
//	this.password = password;
//}
//public String getEmail() {
//	return email;
//}
//public void setEmail(String email) {
//	this.email = email;
//}
 
 
}
