package com.cdj.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // user클래스가 sql에 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//빌더 패턴
public class User {
	
	@Id//프라이머리 키가 필요하다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto-increment
	
	@Column(nullable = false,length = 30) //아이디 비밀번호 이메일 비어 있으면 안되니깐 설정
	private String username; 
	@Column(nullable = false,length = 100)
	private String password; 
	@Column(nullable =false,length = 50)
	private String email;
	@ColumnDefault("'user'")
	private String role; //Enum을 쓰는게 좋다. (권한이 필요할때 쓴다.)
	@CreationTimestamp// 시간이 자동으로 입력
	private Timestamp createDate;

}
