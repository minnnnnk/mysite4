package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	
	public List<GalleryVo> galleryList() {
		System.out.println("GalleryDao > galleryList");
		List<GalleryVo> gVo = sqlSession.selectList("gallery.galleryList");
		
		return gVo;
	}
	
	
	public int gallerySave(GalleryVo galleryVo) {
		
		int count = sqlSession.insert("gallery.galleryInsert", galleryVo);
		
		return count;
	}
	
	public GalleryVo getImage(String saveName) {
		
		GalleryVo gVo = sqlSession.selectOne("gallery.getImage", saveName);
		
		return gVo;
	}
	
	public int galleryDelete(int no) {
		
		int count = sqlSession.delete("gallery.galleryDelete", no);
		
		return count;
	}
}
