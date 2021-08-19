package com.ksa.fullstack.bbs.dao;

import java.util.List;

import com.ksa.fullstack.bbs.dto.BBSDto;
import com.ksa.fullstack.bbs.dto.FileDto;

public interface BBSDao {
	
	public void write(BBSDto article);
	public List<BBSDto> list();
	public BBSDto content(int articleNum);
	public String login(String id);
	public void delete(String articleNum);
	public BBSDto getUpdateArticle(String articleNum);
	public void updateArticle(BBSDto article);
	public void insertFile(FileDto fileDto);
	public List<FileDto> getFiles(int articleNum);
	public int commentCount(int articleNum);
	public int getTotalCount();
	
}
