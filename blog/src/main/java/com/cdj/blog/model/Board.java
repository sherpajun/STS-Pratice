package com.cdj.blog.model;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// user클래스가 sql에 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//빌더 패턴
@Entity 
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length= 100)
	private String title;
	
	@Lob// 대용량 데이터일때 사용
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne//Many =board , User = one 여러개의 개시글은 한명의 유저가 쓸수있다.
	@JoinColumn(name="userid")
	private User user; //DB는 오브젝트를 저장 할 수 없다. 포린키, 자바는 오브젝트를 저장할 수 있다.JPA를 사용하면 오브젝트로 그대로 인식이 가능하다
	
//	@OneToMany(mappedBy ="board")//.연관관계 주인이 아님 즉 FK가 아님 =>DB에 컬럼 생성 금지
//	private List<Reply>reply;
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
}
