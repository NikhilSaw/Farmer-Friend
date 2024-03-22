package com.jsp.FarmerFriend_Team05.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.FarmerFriend_Team05.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, String> {

}
