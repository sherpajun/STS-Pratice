package com.ksa.fullstack.bbs.common;
import java.io.File;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // @Named 
public class FileSaveHelper { 
	
	@Resource(name="saveDir")// @Resource�� �̸����� ã�Ƽ� DI����
	private String saveDir;
	
	public String save(MultipartFile uploadFile){		
		String savedFileName=UUID.randomUUID().toString()+"_"+uploadFile.getOriginalFilename();
		try{
			uploadFile.transferTo(new File(saveDir+savedFileName));			
		}catch(Exception e){}	
		
	    return savedFileName;
	}
}










