package com.jsp.FarmerFriend_Team05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.FarmerFriend_Team05.entity.Comment;
import com.jsp.FarmerFriend_Team05.service.CommentService;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseStructure<Comment>> saveComments(@RequestParam String postId,@RequestParam String userId,@RequestParam String comment) {
		return service.saveComment(postId, userId, comment);
	}
	
	@DeleteMapping("/deleteComment")
	public ResponseEntity<ResponseStructure<Comment>> deleteComments(@RequestParam String commentId) {
		return service.deleteComment(commentId);
	}
}
