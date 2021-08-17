package com.ksa.fullstack.comment.dao;

import java.util.HashMap;
import java.util.List;

import com.ksa.fullstack.comment.dto.CommentDto;


public interface CommentDao {
	public void commentWrite(CommentDto comment);
	public List<CommentDto> commentRead(HashMap<String, Integer> hm);
}
