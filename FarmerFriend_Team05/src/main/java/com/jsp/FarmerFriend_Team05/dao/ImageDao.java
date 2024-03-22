package com.jsp.FarmerFriend_Team05.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Image;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.repo.ImageRepo;

@Repository
public class ImageDao {

	@Autowired
	private ImageRepo repo;
	@Autowired
	private UserDao userDao;

	public Image saveImage(Image image) {
		return repo.save(image);
	}

	public Image fetchImageById(String imageId) {
		return repo.findById(imageId).get();
	}

	public Image deleteUserImage(String userId) {
		User user = userDao.fetchUserById(userId);
		String imgId = user.getImage().getId();
		user.setImage(null);
		userDao.updateUser(user);
		return deleteImage(imgId);
	}

	public Image deleteImage(String imageId) {
		Optional<Image> image = repo.findById(imageId);
		if (image.isPresent()) {
			repo.deleteById(imageId);
			return image.get();
		} else
			return null;
	}

	public Image fetchUserImage(String imageId) {
		 Optional<Image> image = repo.findById(imageId);
		 if (image.isPresent()) {
			return image.get();
		} else {
			return null;
		}
	}

}
