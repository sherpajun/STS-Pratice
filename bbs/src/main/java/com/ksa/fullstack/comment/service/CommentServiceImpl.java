package com.ksa.fullstack.comment.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksa.fullstack.comment.dao.CommentDao;
import com.ksa.fullstack.comment.dto.CommentDto;

@Service

public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<CommentDto> commentWrite(CommentDto comment) {
		commentDao.commentWrite(comment);
		HashMap<String,Integer> hm = new HashMap< >();
		hm.put("articleNum", comment.getArticleNum());
		hm.put("commentRow",10);
		return commentDao.commentRead(hm);

	}


}
