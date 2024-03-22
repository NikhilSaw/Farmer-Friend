package com.jsp.FarmerFriend_Team05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.FarmerFriend_Team05.entity.Image;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.service.UserService;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/saveUser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		return service.saveUser(user);	
	}
	
	@GetMapping("/loginUser")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email, @RequestParam String password){
		return service.loginUser(email, password);
	}
	
	@GetMapping("/sendOtp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email){
		return service.sendOtp(email);
	}
	
	@PostMapping("/profilePicUpload")
	public ResponseEntity<ResponseStructure<Image>> uploadProfilePic(@RequestParam String userId, @RequestParam MultipartFile file){
		return service.uploadProfilePic(userId, file);	
	}
	
	@GetMapping("/fetchProfilePic")
	public ResponseEntity<byte[]> fetchProfilePic(@RequestParam String userId){
		return service.fetchProfilePic(userId);	
	}
	
	@DeleteMapping("/profilePicDelete")
	public ResponseEntity<ResponseStructure<Image>> deleteProfilePic(@RequestParam String userId){
		return service.deleteProfilePic(userId);	
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam String userId){
		return service.deleteUser(userId);
	}
	
	@GetMapping("/fetchUser")
	public ResponseEntity<ResponseStructure<User>> fetchUser(@RequestParam String userId){
		return service.fetchUser(userId);
	}
	
	@PutMapping("/editUser")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
}
