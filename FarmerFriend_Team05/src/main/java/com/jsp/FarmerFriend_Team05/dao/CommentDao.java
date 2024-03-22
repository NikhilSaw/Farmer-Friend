package com.jsp.FarmerFriend_Team05.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Comment;
import com.jsp.FarmerFriend_Team05.entity.Post;
import com.jsp.FarmerFriend_Team05.repo.CommentRepo;
import com.jsp.FarmerFriend_Team05.repo.PostRepo;

@Repository
public class CommentDao {

	@Autowired
	private CommentRepo repo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private PostDao postDao;

	public Comment saveComment(Comment comment) {
		return repo.save(comment);
	}

	public Comment deleteComment(String commentId) {
		List<Post> posts = postRepo.findAll();
	    for (Post post : posts) {
	        List<Comment> comments = post.getComments();
	        Iterator<Comment> iterator = comments.iterator();
	        while (iterator.hasNext()) {
	            Comment comment = iterator.next();
	            if (comment.getId().equals(commentId)) {
	               iterator.remove();
	                postDao.updatePost(post);
	                comment.setUser(null);
	                repo.deleteById(commentId);
	                return comment;
	            }
	        }
	    }
	    return null;
	}

}
