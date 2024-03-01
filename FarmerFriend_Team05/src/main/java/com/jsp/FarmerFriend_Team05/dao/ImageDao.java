package com.jsp.FarmerFriend_Team05.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Image;
import com.jsp.FarmerFriend_Team05.repo.ImageRepo;

@Repository
public class ImageDao {

	@Autowired
	private ImageRepo repo;
	
	public Image saveImage(Image image) {
		return repo.save(image);
	}
	
}
