package com.jsp.FarmerFriend_Team05.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo repo;

	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUserById(int id) {
		Optional<User> db = repo.findById(id);
		if (!db.isEmpty()) {
			return db.get();
		} else {
			return null;
		}
	}

	public User deleteUserById(int id) {
		Optional<User> db = repo.findById(id);
		if (!db.isEmpty()) {
			repo.deleteById(id);
			return db.get();
		} else {
			return null;
		}
	}

	public User updateUser(User user) {
	    Optional<User> db = repo.findById(user.getId());
	    if (db.isPresent()) {
	        User u = db.get();
	        if (user.getAddress() != null) u.setAddress(user.getAddress());
            if (user.getAge() != 0) u.setAge(user.getAge());
            if (user.getEmail() != null) u.setEmail(user.getEmail());
            if (user.getFirstName() != null) u.setFirstName(user.getFirstName());
            if (user.getLastName() != null) u.setLastName(user.getLastName());
            if (user.getGender() != null) u.setGender(user.getGender());
            if (user.getPassword() != null) u.setPassword(user.getPassword());
            if (user.getPhone() != 0) u.setPhone(user.getPhone());
            if (user.getType() != null) u.setType(user.getType());
	        return repo.save(u);
	    } else {
	        return null;
	    }
	}

	public User fetchUserbyEmail(String email) {
		return repo.findByEmail(email);
	}
}
