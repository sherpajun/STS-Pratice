package com.ksa.fullstack.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ksa.fullstack.common.MediaUtils;
import com.ksa.fullstack.common.UploadFileUtil;

@Controller
public class AjaxController {
	
	@Autowired
	private UploadFileUtil ufu;
	
	@Resource(name="saveDir")
	private String saveDir;
	
	@GetMapping("/main.bbs")
	public String main() {
		return "uploadAjax";
	}
	
	@PostMapping("/uploadAjax.bbs")
	@ResponseBody
	public List<String> uploadAjx(@RequestPart("multiFile") List<MultipartFile> multiFile) throws Exception{
		List<String> fileList = new ArrayList<>();
		for(MultipartFile file :multiFile ) {
			fileList.add(ufu.uploadFile(saveDir, file.getOriginalFilename(),file.getBytes()));
		}		
		
		return fileList;
	}
	
	@ResponseBody
	@GetMapping("/displayFile.bbs")
	public ResponseEntity<byte[]>  displayFile(@RequestParam("fileName") String fileName) throws Exception{
		System.out.println(fileName);
		InputStream in = null; 
		ResponseEntity<byte[]> entity = null;
		try{      
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);      
			HttpHeaders headers = new HttpHeaders();      
			in = new FileInputStream(saveDir+fileName);	
			if(mType != null){
				headers.setContentType(mType);
			}else{			      
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);			
			}			
			fileName = fileName.substring(fileName.indexOf("_")+1); 
			headers.add("Content-Disposition","attachment; filename=\""+ 
					URLEncoder.encode(fileName,"utf-8").replace("+","%20")+"\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(
					HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		return entity;    
	}

	@ResponseBody
	@PostMapping(value="/deleteFile.bbs")
	public ResponseEntity<String> deleteFile(String fileName){		   
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);    
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		new File(saveDir + fileName.replace('/', File.separatorChar)).delete();

		if(mType != null){      
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(saveDir+(front+end).replace('/', File.separatorChar)).delete();
		}
  
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}  
	
	@ResponseBody
	@PostMapping(value="/deleteAllFiles.bbs")
//	ajax() �Լ��� �迭�� ����ȭ ���� �ʰ� ��������..�Ʒ� �ڵ�ó�� �ص� ó����
//	public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files){
	public ResponseEntity<String> deleteFile(@RequestParam("files") String[] files){		   
		if(files == null || files.length == 0) {
			return new ResponseEntity<String>	("deleted", HttpStatus.OK);
		}
		for (String fileName : files) {
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);      
			MediaType mType = MediaUtils.getMediaType(formatName);      
			if(mType != null){        
				String front = fileName.substring(0,12);
				String end = fileName.substring(14);
				new File(saveDir + (front+end).replace('/', File.separatorChar)).delete();
			}
			new File(saveDir + fileName.replace('/', File.separatorChar)).delete();      
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
