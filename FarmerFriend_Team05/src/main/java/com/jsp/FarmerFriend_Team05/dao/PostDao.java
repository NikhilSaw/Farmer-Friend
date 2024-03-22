package com.jsp.FarmerFriend_Team05.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Post;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.repo.PostRepo;
import com.jsp.FarmerFriend_Team05.repo.UserRepo;

@Repository
public class PostDao {

	@Autowired
	private PostRepo repo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserDao userDao;

	public Post savePost(Post post) {
		return repo.save(post);
	}

	public Post findPostById(String postId) {
		Optional<Post> post = repo.findById(postId);
		if (post.isPresent()) {
			return post.get();
		} else {
			return null;
		}
	}

	public Post updatePost(Post post) {
		Optional<Post> db = repo.findById(post.getId());
		if (db.isPresent()) {
			Post postdb = db.get();
			if (post.getCaption() != null)
				postdb.setCaption(post.getCaption());
			if (post.getLocation() != null)
				postdb.setLocation(post.getLocation());
			if (post.getDateTime() != null)
				postdb.setDateTime(post.getDateTime());
			if (post.getImage() != null)
				postdb.setImage(post.getImage());
			if (post.getComments() != null)
				postdb.setComments(post.getComments());
			return repo.save(postdb);
		} else {
			return null;
		}
	}

	public Post deletePostById(String postId) {
		List<User> users = userRepo.findAll();
		for (User user : users) {
			List<Post> posts = user.getPost();
			Iterator<Post> iterator = posts.iterator();
			while (iterator.hasNext()) {
				Post post = iterator.next();
				if (post.getId().equals(postId)) {
					iterator.remove();
					userDao.updateUser(user);
					repo.deleteById(postId);
					return post;
				}
			}
		}
		return null;
	}

}
