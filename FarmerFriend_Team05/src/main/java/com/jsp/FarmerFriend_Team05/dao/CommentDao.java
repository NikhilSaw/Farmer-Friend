package com.jsp.FarmerFriend_Team05.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Comment;
import com.jsp.FarmerFriend_Team05.repo.CommentRepo;

@Repository
public class CommentDao {

	@Autowired
	private CommentRepo repo;
	
	public Comment saveComment(Comment comment) {
		return repo.save(comment);
	}
	
}
