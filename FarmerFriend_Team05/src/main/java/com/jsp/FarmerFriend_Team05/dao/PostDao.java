package com.jsp.FarmerFriend_Team05.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsp.FarmerFriend_Team05.entity.Post;
import com.jsp.FarmerFriend_Team05.repo.PostRepo;

public class PostDao {

	@Autowired
	private PostRepo repo;
	
	public Post savePost(Post post) {
		return repo.save(post);
	}
	
}
