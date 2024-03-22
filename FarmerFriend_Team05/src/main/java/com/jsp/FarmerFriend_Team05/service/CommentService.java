package com.jsp.FarmerFriend_Team05.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.FarmerFriend_Team05.dao.CommentDao;
import com.jsp.FarmerFriend_Team05.dao.PostDao;
import com.jsp.FarmerFriend_Team05.dao.UserDao;
import com.jsp.FarmerFriend_Team05.entity.Comment;
import com.jsp.FarmerFriend_Team05.entity.Post;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.exception.CommentNotFoundException;
import com.jsp.FarmerFriend_Team05.exception.PostNotFoundException;
import com.jsp.FarmerFriend_Team05.exception.UserNotFoundException;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PostDao postDao;

	public ResponseEntity<ResponseStructure<Comment>> saveComment(String postId, String userId, String comment) {
		Post postDb = postDao.findPostById(postId);

		if (postDb != null) {
			User userDB = userDao.fetchUserById(userId);
			if (userDB != null) {
				Comment cm = new Comment();
				cm.setComment(comment);
				cm.setUser(userDB);
				Comment comments = commentDao.saveComment(cm);
				Post p = postDb;
				List<Comment> postComment = new ArrayList<Comment>();
				postComment.add(comments);
				postComment.addAll(p.getComments());

				p.setComments(postComment);
				postDao.updatePost(p);
				ResponseStructure<Comment> m = new ResponseStructure<Comment>();
				m.setData(cm);
				m.setMessage("Comment Posted Successfully");
				m.setStatus(HttpStatus.OK.value());

				return new ResponseEntity<ResponseStructure<Comment>>(m, HttpStatus.OK);
			} else {
				throw new UserNotFoundException("No User found with Id = " + userId);
			}
		} else {
			throw new PostNotFoundException("No Post Found with ID = " + postId);
		}
	}

	public ResponseEntity<ResponseStructure<Comment>> deleteComment(String commentId) {
	    Comment db = commentDao.deleteComment(commentId);
	    if (db != null) {
	        ResponseStructure<Comment> r = new ResponseStructure<Comment>();
	        r.setData(db);
	        r.setMessage("Comment Deleted Successfully");
	        r.setStatus(HttpStatus.NO_CONTENT.value());
	        return new ResponseEntity<ResponseStructure<Comment>>(r, HttpStatus.NO_CONTENT);
	    } else {
	        throw new CommentNotFoundException("Comment not found with Id = " + commentId);
	    }
	}
	
}