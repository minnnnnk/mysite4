package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	
	@Autowired
	private GalleryDao galleryDao;
	
	//리스트
	public List<GalleryVo> galleryList(){
		System.out.println("GalleryService > galleryList");
		
		List<GalleryVo> gVo = galleryDao.galleryList();
		
		
		return gVo;
	}
	
	//파일 하드디스크 저장, 파일정보(DB) 추출 저장
		public String gallerySave(MultipartFile file, GalleryVo galleryVo) {
			System.out.println("GalleryService > save(");
			
			String saveDir = "C:\\javaStudy\\upload";
			
			//(1)파일 정보(DB)저장 추출 저장
			//오리지날파일명, 저장경로+파일(랜덤)명, 파일 사이즈
			
			//오리지널 파일명
			String orgName = file.getOriginalFilename();
			
			//확장자
			String exName = orgName.substring(orgName.lastIndexOf("."));
			
			//저장파일명
			String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
			
			//파일 경로(디렉토리+저장파일명)
			String filePath = saveDir + "\\" + saveName;
			
			//파일사이즈
			long fileSize = file.getSize();
			
			
			//Vo로 묶기
			
			galleryVo.setOrgName(orgName);
			galleryVo.setSaveName(saveName);
			galleryVo.setFilePath(filePath);
			galleryVo.setFileSize(fileSize);
			
			//다오--DB저장
			
			galleryDao.gallerySave(galleryVo);
			
			//(2)파일 저장
			try {
				byte[] fileData = file.getBytes();
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileData);
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return saveName;
			
		}
	
	
		public GalleryVo getImage(String saveName) {
			
			GalleryVo gVo =galleryDao.getImage(saveName);
			
			return gVo;
		}
		
		public int delete(int no) {
			
			
			int count =galleryDao.galleryDelete(no);
			
			System.out.println(count+"건 삭제되엇습니다");
			
			return count;
		}
}	
