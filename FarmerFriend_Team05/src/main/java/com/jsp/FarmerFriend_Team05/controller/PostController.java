package com.jsp.FarmerFriend_Team05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.FarmerFriend_Team05.entity.Post;
import com.jsp.FarmerFriend_Team05.service.PostService;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@RestController
public class PostController {

	@Autowired
	private PostService service;
	
	@PostMapping("/postUpload")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam String userId,@RequestParam MultipartFile file,@RequestParam String caption,@RequestParam String location) {
		return service.savePost(userId, file, caption, location);
	}
	
	@GetMapping("/postFetch")
	public ResponseEntity<ResponseStructure<Post>> fetchPost(@RequestParam String postId) {
		return service.fetchPostById(postId);
	}
	
	@DeleteMapping("/postDelete")
	public ResponseEntity<ResponseStructure<Post>> deletePost(@RequestParam String postId) {
		return service.deletePostById(postId);
	}
}

