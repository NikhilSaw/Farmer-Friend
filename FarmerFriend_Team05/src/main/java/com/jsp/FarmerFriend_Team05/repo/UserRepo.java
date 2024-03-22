package com.jsp.FarmerFriend_Team05.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.FarmerFriend_Team05.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

	@Query("Select a from User a where email=?1")
	public User findByEmail(String email);

	@Query(value = "Select user_id from user_post where post_id = ?1", nativeQuery = true)
	public String findUserIdByPostId(String postId);

}