package com.ksa.fullstack.bbs.service;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ksa.fullstack.bbs.common.FileSaveHelper;
import com.ksa.fullstack.bbs.dao.BBSDao;
import com.ksa.fullstack.bbs.dto.BBSDto;
import com.ksa.fullstack.bbs.dto.FileDto;

@Service
public class BBSServiceImpl implements BBSService {
	
	@Autowired
	private BBSDao bbsDao;
	
	@Autowired
	private FileSaveHelper fileSaveHelper;
	
	@Resource(name="saveDir")// @Resource는 이름으로 찾아서 DI해줌
	private String saveDir;

	@Override
	public void write(BBSDto article, List<MultipartFile> fileUpload) {	
//		if(fileUpload.size()!=0){// 오류가 있음
//		if(!fileUpload.isEmpty()){// 오류가 있음
//			System.out.println("파일업로드가 있습니다");
//		}
//		List<MultipartFile>는 CommonsMultipartFile을 리턴함
		//		그래서 isEmpty()(List에 있는 isEmpty()메소드임)로 물어보면 List값이 
		//		비어있는것이 아님
		//		MultipartFile에 있는 isEmpty()를 사용해야함
		System.out.println("글쓰기 전의 articleNum "+article.getArticleNum());
		bbsDao.write(article);	
		System.out.println("글쓰기 후의 articleNum "+article.getArticleNum());
		
		if(!fileUpload.get(0).isEmpty()) {
			for(MultipartFile mf:fileUpload) {
				String savedFileName=fileSaveHelper.save(mf);
				FileDto fileDto = new FileDto();
				fileDto.setOriginalFileName(mf.getOriginalFilename());
				fileDto.setSavedFileName(savedFileName);
				fileDto.setArticleNum(article.getArticleNum());
				bbsDao.insertFile(fileDto);
			}
		}
	}

	@Override
	public List<BBSDto> list() {		
		return bbsDao.list();
	}

	@Override
	public BBSDto content(int articleNum) {			
		return bbsDao.content(articleNum);
	}

	@Override
	public String login(String id, String pass, HttpSession session) {
		String dbPass=bbsDao.login(id);
		
		if(dbPass==null) {
			System.out.println("회원 아님");
			return null;
		}else if(dbPass.equals(pass)) {
			session.setAttribute("id", id);			
			return "redirect:/list.bbs";			
		}else {
			return "login";
		}			
	}

	@Override
	public void delete(String articleNum) {
		bbsDao.delete(articleNum);		
	}

	@Override
	public BBSDto getUpdateArticle(String articleNum) {
		return bbsDao.getUpdateArticle(articleNum);
	}

	@Override
	public void updateArticle(BBSDto article) {
		bbsDao.updateArticle(article);
		
	}

	@Override
	public List<FileDto> getFiles(int articleNum) {		
		return bbsDao.getFiles(articleNum);
	}
	
	@Override
	public FileSystemResource download(String savedFileName, HttpServletResponse res) {
		res.setContentType("application/download");		
//		res.setContentLength(fileLength);
		String originalFileName=savedFileName.substring(savedFileName.indexOf("_")+1);
		try{
			originalFileName = URLEncoder.encode(originalFileName,"utf-8").replace("+","%20").
					replace("%28", "(").replace("%29", ")");
		}catch(Exception e){
		}

		res.setHeader("Content-Disposition", "attachment;" +" filename=\""+originalFileName+ "\";");
		FileSystemResource fsr= new FileSystemResource(saveDir+savedFileName);
		return fsr;
	
	
	}

	@Override
	public int commentCount(int articleNum) {
		return bbsDao.commentCount(articleNum);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
