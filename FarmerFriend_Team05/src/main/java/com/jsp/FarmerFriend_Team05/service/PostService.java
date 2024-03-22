package com.jsp.FarmerFriend_Team05.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.FarmerFriend_Team05.dao.PostDao;
import com.jsp.FarmerFriend_Team05.dao.UserDao;
import com.jsp.FarmerFriend_Team05.entity.Image;
import com.jsp.FarmerFriend_Team05.entity.Post;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.exception.ImageUploadException;
import com.jsp.FarmerFriend_Team05.exception.PostNotFoundException;
import com.jsp.FarmerFriend_Team05.exception.UserNotFoundException;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Post>> savePost(String userId, MultipartFile file, String caption,
			String location) {

		ResponseStructure<Post> m = new ResponseStructure<Post>();
		User db = userDao.fetchUserById(userId);
		if (db != null) {
			Post post = new Post();
			Image image = new Image();
			image.setImageName(file.getOriginalFilename());
			image.setType(file.getContentType());
			try {
				image.setImage(file.getBytes());
			} catch (IOException e) {
				throw new ImageUploadException("Sorry :( Image cann't be Uploded !!! X ERROR OCCURED X");
			}
			post.setImage(image);
			post.setCaption(caption);
			post.setLocation(location);
			post.setDateTime(LocalDateTime.now());
			List<Post> p = new ArrayList<Post>();
			p.add(post);
			p.addAll(db.getPost());
			db.setPost(p);
			userDao.updateUser(db);
			m.setData(post);
			m.setMessage("Post Uploaded Successfully...");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.OK);
		} else {
			throw new UserNotFoundException("User not Found with Given Id = " + userId);
		}
	}

	public ResponseEntity<ResponseStructure<Post>> fetchPostById(String postId) {
		ResponseStructure<Post> m = new ResponseStructure<Post>();
		Post db = postDao.findPostById(postId);
		if (db != null) {
			m.setData(db);
			m.setMessage("fetch Post Successfully...");
			m.setStatus(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.FOUND);
		} else {
			throw new PostNotFoundException("Post not found with Id = " + postId);
		}
	}

	public ResponseEntity<ResponseStructure<Post>> deletePostById(String postId) {

		ResponseStructure<Post> m = new ResponseStructure<Post>();
		Post post = postDao.findPostById(postId);
		if (post != null) {
			m.setData(postDao.deletePostById(postId));
			m.setMessage("Post is Deleted Successfully...");
			m.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.GONE);
		} else {
			throw new PostNotFoundException("Post not found with Id = " + postId);
		}

	}
}
