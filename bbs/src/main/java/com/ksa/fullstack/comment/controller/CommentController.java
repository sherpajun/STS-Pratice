package com.ksa.fullstack.comment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksa.fullstack.comment.dto.CommentDto;
import com.ksa.fullstack.comment.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@PostMapping("/commentWrite.comment")
	//public void commentWrite(@RequestParam("articleNum")int articleNum,
	//		                 @RequestParam("commentContent") String commentContent,
	//		                 HttpSession session) {
	
	@ResponseBody
	public List<CommentDto> commentWrite(CommentDto comment, HttpSession session) {
		comment.setId((String)session.getAttribute("id"));//다운캐스팅
		return commentService.commentWrite(comment);
	}

}
